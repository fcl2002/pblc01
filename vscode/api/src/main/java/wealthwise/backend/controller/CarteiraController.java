package wealthwise.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import wealthwise.backend.domain.Carteira;
import wealthwise.backend.domain.Usuario;
import wealthwise.backend.repositories.UsuarioRepository;
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

    @Autowired
    private UsuarioRepository usuarioRepository;


    // get localhost:8080/carteira/all
    @GetMapping("/all")
    public ResponseEntity<List<Carteira>> getAllCarteiras() {
        List<Carteira> carteiras = carteiraService.getAll();
        return ResponseEntity.ok(carteiras);
    }

    // get localhost:8080/carteira/{string}
    @GetMapping("/{id}")
    public ResponseEntity<Carteira> getId(@PathVariable Long id) {
        Carteira carteira = carteiraService.getId(id).orElse(null);
        return ResponseEntity.ok(carteira);
    }

    // insert
    // post localhost:8080/carteira
    @PostMapping
    public ResponseEntity<Carteira> postCarteira(@RequestBody Carteira carteira) {

        Usuario usuario = usuarioRepository.findById(carteira.getUsuario().getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        Carteira carteira1 = new Carteira();
        carteira1.setName(carteira.getName());
        carteira1.setRisk(carteira.getRisk());
        carteira1.setUsuario(usuario);

        usuario.getCarteiras().add(carteira1);
        usuarioRepository.save(usuario);

        Carteira savedCarteira = carteiraService.create(carteira1);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCarteira);
    }

    // update
    // put localhost:8080/carteira/{string}
    @PutMapping("/{id}")
    public ResponseEntity<Carteira> putCarteira(@RequestBody Carteira carteira, @PathVariable Long id) {
        Carteira carteira1 = carteiraService.updateCarteira(carteira, id);
        return ResponseEntity.ok(carteira1);
    }

    // delete
    // delete localhost:8080/carteira/{string}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCarteira(@PathVariable Long id) {
        carteiraService.deleteId(id);
        return ResponseEntity.ok("Carteira deleted (id: " + id + ")");
    }
}
