package wealthwise.backend.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import wealthwise.backend.domain.VariableIncome;
import wealthwise.backend.dtos.asset.VariableRequestDTO;
import wealthwise.backend.dtos.asset.VariableResponseDTO;
import wealthwise.backend.repositories.VariableIncomeRepository;
import wealthwise.backend.services.VariableIncomeService;

@RestController
@RequestMapping("/incomes/variable")
public class VariableIncomeController {

    @Autowired
    private VariableIncomeRepository repository;

    @Autowired
    private VariableIncomeService service;

    @GetMapping
    public ResponseEntity<List<VariableResponseDTO>> getAll() {
        
        List<VariableResponseDTO> incomes = this.repository.findAll().stream().map(VariableResponseDTO::new).toList();
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VariableResponseDTO> getVariableByName(@PathVariable String id) {
        
        VariableIncome income = service.getVariableById(id);
        VariableResponseDTO incomeDTO = new VariableResponseDTO(income);
        return ResponseEntity.ok(incomeDTO);
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<VariableResponseDTO> getVariableByTicker(@PathVariable String ticker) {
        
        VariableIncome income = service.getVariableByTicker(ticker);
        VariableResponseDTO incomeDTO = new VariableResponseDTO(income);
        return ResponseEntity.ok(incomeDTO);
    }

    @PostMapping
    public ResponseEntity<VariableResponseDTO> postVariable(@RequestBody @Valid VariableRequestDTO request) {
        
        VariableResponseDTO incomeDTO = service.createVariableIncome(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(incomeDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VariableResponseDTO> putVariavel(@RequestBody Map<String, Object> updates, @PathVariable String id) {
        
        VariableResponseDTO incomeDTO = service.updateVariable(updates, id);
        return ResponseEntity.status(HttpStatus.OK).body(incomeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVariable(@PathVariable String id) {
        
        service.deleteVariable(id);
        return ResponseEntity.ok("Variable Income deleted with id '" + id + "'");
    }
}