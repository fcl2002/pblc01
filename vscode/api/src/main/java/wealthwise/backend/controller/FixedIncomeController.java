package wealthwise.backend.controller;

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
import wealthwise.backend.domain.FixedIncome;
import wealthwise.backend.dtos.asset.FixedRequestDTO;
import wealthwise.backend.dtos.asset.FixedResponseDTO;
import wealthwise.backend.repositories.FixedIncomeRepository;
import wealthwise.backend.services.FixedIncomeService;


@RestController
@RequestMapping("/incomes/fixed")
public class FixedIncomeController {

    @Autowired
    private FixedIncomeRepository repository;

    @Autowired
    private FixedIncomeService service;

    @GetMapping
    public ResponseEntity<List<FixedResponseDTO>> getAll() {
        
        List<FixedResponseDTO> incomes = this.repository.findAll().stream().map(FixedResponseDTO::new).toList();
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FixedResponseDTO> getVariableByName(@PathVariable String id) {
        
        FixedIncome income = service.getFixedById(id);
        FixedResponseDTO incomeDTO = new FixedResponseDTO(income);
        return ResponseEntity.ok(incomeDTO);
    }

    @PostMapping
    public ResponseEntity<FixedResponseDTO> postVariable(@RequestBody @Valid FixedRequestDTO request) {
        
        FixedResponseDTO incomeDTO = service.createFixedIncome(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(incomeDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FixedResponseDTO> putVariavel(@RequestBody Map<String, Object> updates, @PathVariable String id) {
        
        FixedResponseDTO incomeDTO = service.updateFixed(updates, id);
        return ResponseEntity.status(HttpStatus.OK).body(incomeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVariable(@PathVariable String id) {
        
        service.deleteFixed(id);
        return ResponseEntity.ok("Ativo Variavel deleted (id: " + id + ")");
    }

}
