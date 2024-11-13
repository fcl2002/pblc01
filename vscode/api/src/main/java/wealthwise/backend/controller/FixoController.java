package wealthwise.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wealthwise.backend.domain.Fixo;
import wealthwise.backend.services.FixoService;

import org.springframework.beans.factory.annotation.Autowired;
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

    // get localhost:8080/ativofixo/all
    @GetMapping("/all")
    public List<Fixo> getAll() {
        return fixoService.getAll();
    }

    // get localhost:8080/ativofixo/{string}
    @GetMapping("/{string}")
    public Fixo getAtivoFixo(@PathVariable String name) {
        return fixoService.getId(name).orElse(null);
    }

    // insert
    // post localhost:8080/ativofixo
    @PostMapping
    public Fixo postAtivoFixo(@RequestBody Fixo ativoFixo) {
        return fixoService.create(ativoFixo);
    }

    // update
    // put localhost:8080/ativofixo/{string}
    @PutMapping("/{uuid}")
    public Fixo putAtivoFixo(@RequestBody Fixo ativoFixo, @PathVariable UUID uuid) {
        return fixoService.update(ativoFixo);
    }

    // delete
    // delete localhost:8080/ativo/{string}
    @DeleteMapping("/{uuid}")
    public void deleteAtivoFixo(@PathVariable UUID id) {
        fixoService.deleteId(id);
    }
}
