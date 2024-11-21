package wealthwise.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wealthwise.backend.domain.Carteira;
import wealthwise.backend.services.CarteiraService;

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
@RequestMapping("/carteira")
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @GetMapping("/all")
    public ResponseEntity<List<Carteira>> getAllCarteiras() {
        List<Carteira> carteiras = carteiraService.getAll();
        return ResponseEntity.ok(carteiras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carteira> getId(@PathVariable Long id) {
        Carteira carteira = carteiraService.getId(id).orElse(null);
        return ResponseEntity.ok(carteira);
    }

    @PostMapping
    public ResponseEntity<Carteira> postCarteira(@RequestBody Carteira carteira) {
        Carteira savedCarteira = carteiraService.createCarteira(carteira);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCarteira);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carteira> putCarteira(@RequestBody Carteira carteira, @PathVariable Long id) {
        Carteira carteira1 = carteiraService.updateCarteira(carteira, id);
        return ResponseEntity.ok(carteira1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarteira(@PathVariable Long id) {
        carteiraService.deleteId(id);
        return ResponseEntity.ok("Carteira deleted (id: " + id + ")");
    }
}
