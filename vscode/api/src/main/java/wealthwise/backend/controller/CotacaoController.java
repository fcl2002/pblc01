package wealthwise.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wealthwise.backend.domain.Cotacao;
import wealthwise.backend.services.CotacaoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cotacao")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @GetMapping("/all")
    public ResponseEntity<List<Cotacao>> getAll() {
        List<Cotacao> cotacoes = cotacaoService.getAll();
        return ResponseEntity.ok(cotacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cotacao> getId(@PathVariable Long id) {
        Cotacao cotacao = cotacaoService.getId(id).orElse(null);
        return ResponseEntity.ok(cotacao);
    }

    @PostMapping
    public ResponseEntity<Cotacao> postUser(@RequestBody Cotacao cotacao) {
        Cotacao user = cotacaoService.createCotacao(cotacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cotacao> updateCotacao(@RequestBody Cotacao cotacao, @PathVariable Long id) {
        Cotacao user = cotacaoService.update(cotacao);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCotacao(@PathVariable Long id) {
        cotacaoService.deleteCotacao(id);
        return ResponseEntity.ok("Cotacao deleted (id: " + id + ")");
    }
}
