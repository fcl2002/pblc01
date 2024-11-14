package wealthwise.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wealthwise.backend.domain.Cotacao;
import wealthwise.backend.services.CotacaoService;

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

    // get localhost:8080/cotacao/all
    @GetMapping("/all")
    public List<Cotacao> getAll() {
        return cotacaoService.getAll();
    }

    // get localhost:8080/cotacao/{string}
    @GetMapping("/{id}")
    public Cotacao getId(@PathVariable Long id) {
        return cotacaoService.getId(id).orElse(null);
    }

    // insert
    // post localhost:8080/cotacao
    @PostMapping
    public Cotacao postUsuario(@RequestBody Cotacao cotacao) {
        return cotacaoService.create(cotacao);
    }

    // update
    // put localhost:8080/cotacao/{string}
    @PutMapping("/{id}")
    public Cotacao putUsuario(@RequestBody Cotacao cotacao, @PathVariable Long id) {
        return cotacaoService.update(cotacao);
    }

    // delete
    // delete localhost:8080/cotacao/{string}
    @DeleteMapping("/{id}")
    public void deleteId(@PathVariable Long id) {
        cotacaoService.deleteId(id);
    }
}
