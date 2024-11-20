package wealthwise.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wealthwise.backend.domain.Fixo;
import wealthwise.backend.services.FixoService;

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
@RequestMapping("/ativofixo")
public class FixoController {

    @Autowired
    private FixoService fixoService;

    @GetMapping("/all")
    public ResponseEntity<List<Fixo>> getAllFixo() {
        List<Fixo> fixos = fixoService.getAll();
        return ResponseEntity.ok(fixos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fixo> getId(@PathVariable Long id) {
        Fixo fixo = fixoService.getId(id).orElse(null);
        return ResponseEntity.ok(fixo);
    }

    @PostMapping
    public ResponseEntity<Fixo> postFixo(@RequestBody Fixo fixo) {
        Fixo savedFixo = fixoService.createFixo(fixo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFixo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fixo> putFixo(@RequestBody Fixo fixo, @PathVariable Long id) {
        Fixo fixo1 = fixoService.updateFixo(fixo, id);
        return ResponseEntity.ok(fixo1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFixo(@PathVariable Long id) {
        fixoService.deleteId(id);
        return ResponseEntity.ok("Ativo Fixo deleted (id: " + id + ")");
    }
}
