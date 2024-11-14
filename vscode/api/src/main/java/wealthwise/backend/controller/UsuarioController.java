package wealthwise.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wealthwise.backend.domain.Usuario;
import wealthwise.backend.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/all")
    public List<Usuario> getAll() {
        return usuarioService.getAll();
    }

    @GetMapping("/{username}")
    public Usuario getUsername(@PathVariable String username) {
        return usuarioService.getId(username).orElse(null);
    }

    @PostMapping
    public Usuario postUsuario(@RequestBody Usuario usuario) {
        return usuarioService.create(usuario);
    }

    @PutMapping("/{username}")
    public Usuario putUsuario(@RequestBody Usuario usuario, @PathVariable String username) {
        return usuarioService.update(usuario);
    }

    // delete localhost:8080/usuario/{string}
    @DeleteMapping("/{username}")
    public void deleteUsername(@PathVariable String username) {
        usuarioService.deleteId(username);
    }
}
