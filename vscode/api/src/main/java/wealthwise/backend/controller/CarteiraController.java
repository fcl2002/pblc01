package wealthwise.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wealthwise.backend.domain.Carteira;
import wealthwise.backend.services.CarteiraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/carteira")
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    // get localhost:8080/carteira/all
    @GetMapping("/all")
    public List<Carteira> getAll() {
        return carteiraService.getAll();
    }

    // get localhost:8080/carteira/{string}
    @GetMapping("/{uuid}")
    public Carteira getId(@PathVariable UUID id) {
        return carteiraService.getId(id).orElse(null);
    }

    // insert
    // post localhost:8080/carteira
    @PostMapping
    public Carteira postCarteira(@RequestBody Carteira carteira) {
        return carteiraService.create(carteira);
    }

    // update
    // put localhost:8080/carteira/{string}
    @PutMapping("/{uuid}")
    public Carteira putCarteira(@RequestBody Carteira carteira, @PathVariable UUID uuid) {
        return carteiraService.update(carteira);
    }

    // delete
    // delete localhost:8080/carteira/{string}
    @DeleteMapping("/{uuid}")
    public void deleteId(@PathVariable UUID id) {
        carteiraService.deleteId(id);
    }
}
