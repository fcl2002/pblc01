package wealthwise.backend.controller;

import java.util.List;

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

    @GetMapping("/all")
    public List<Fixo> getAll() {
        return fixoService.getAll();
    }

    @GetMapping("/{id}")
    public Fixo getAtivoFixoByName(@PathVariable Long id) {
        return fixoService.getId(id).orElse(null);
    }

    @PostMapping
    public Fixo postAtivoFixo(@RequestBody Fixo ativoFixo) {
        return fixoService.create(ativoFixo);
    }

    @PutMapping("/{id}")
    public Fixo putAtivoFixo(@RequestBody Fixo ativoFixo, @PathVariable Long id) {
        return fixoService.update(ativoFixo);
    }

    @DeleteMapping("/{id}")
    public void deleteAtivoFixo(@PathVariable Long id) {
        fixoService.deleteId(id);
    }
}
