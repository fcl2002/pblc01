package wealthwise.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wealthwise.backend.domain.Variavel;
import wealthwise.backend.services.VariavelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/ativovariavel")
public class VariavelController {

    @Autowired
    private VariavelService variavelService;

    @GetMapping("/all")
    public ResponseEntity<List<Variavel>> getAll() {
        List<Variavel> variaveis = variavelService.getAll();
        return ResponseEntity.ok(variaveis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Variavel> getAtivoVariavelByName(@PathVariable Long id) {
        Variavel variavel = variavelService.getId(id).orElse(null);
        return ResponseEntity.ok(variavel);
    }

    @PostMapping
    public ResponseEntity<Variavel> postAtivoVariavel(@RequestBody Variavel ativoVariavel) {
        Variavel variavel = variavelService.createVariavel(ativoVariavel);
        return ResponseEntity.status(HttpStatus.CREATED).body(variavel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Variavel> putVariavel(@RequestBody Variavel variavel, @PathVariable Long id) {
        Variavel variavel1 = variavelService.updateVariavel(variavel, id);
        return ResponseEntity.ok(variavel1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVariavel(@PathVariable Long id) {
        variavelService.deleteId(id);
        return ResponseEntity.ok("Ativo Variavel deleted (id: " + id + ")");
    }
}
