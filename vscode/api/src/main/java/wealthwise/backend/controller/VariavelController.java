package wealthwise.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wealthwise.backend.domain.Variavel;
import wealthwise.backend.services.VariavelService;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Variavel> getAll() {
        return variavelService.getAll();
    }

    @GetMapping("/{id}")
    public Variavel getAtivoVariavelByName(@PathVariable Long id) {
        return variavelService.getId(id).orElse(null);
    }

    @PostMapping
    public Variavel postAtivoVariavel(@RequestBody Variavel ativoVariavel) {
        return variavelService.create(ativoVariavel);
    }

    @PutMapping("/{id}")
    public Variavel putAtivoVariavel(@RequestBody Variavel ativoVariavel, @PathVariable Long id) {
        return variavelService.update(ativoVariavel);
    }

    @DeleteMapping("/{id}")
    public void deleteAtivoVariavel(@PathVariable Long id) {
        variavelService.deleteId(id);
    }
}
