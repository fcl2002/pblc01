package wealthwise.backend.controller;

import java.util.List;

import wealthwise.backend.domain.Usuario;
import wealthwise.backend.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> users = usuarioService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Usuario> getUser(@PathVariable String username) {
        Usuario user = usuarioService.getId(username).orElse(null);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Usuario> postUser(@RequestBody Usuario usuario) {
        Usuario user = usuarioService.createUser(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<Usuario> updateUser(@RequestBody Usuario usuario, @PathVariable String username) {
        Usuario user = usuarioService.update(usuario);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        usuarioService.deleteUser(username);
        return ResponseEntity.ok("User deleted (id: " + username + ")");
    }
}
