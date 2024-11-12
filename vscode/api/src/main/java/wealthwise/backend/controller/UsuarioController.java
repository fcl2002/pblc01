package wealthwise.backend.controller;

import java.util.List;
import java.util.UUID;

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

    // get localhost:8080/usuario/all
    @GetMapping("/all")
    public List<Usuario> getAll() {
        return usuarioService.getAll();
    }

    // get localhost:8080/usuario/{string}
    @GetMapping("/{uuid}")
    public Usuario getEmail(@PathVariable UUID id) {
        return usuarioService.getId(id).orElse(null);
    }

    // insert
    // post localhost:8080/usuario
    @PostMapping
    public Usuario postUsuario(@RequestBody Usuario usuario) {
        return usuarioService.create(usuario);
    }

    // update
    // put localhost:8080/usuario/{string}
    @PutMapping("/{uuid}")
    public Usuario putUsuario(@RequestBody Usuario usuario, @PathVariable UUID uuid) {
        return usuarioService.update(usuario);
    }

    // delete
    // delete localhost:8080/usuario/{string}
    @DeleteMapping("/{uuid}")
    public void deleteEmail(@PathVariable UUID id) {
        usuarioService.deleteId(id);
    }
}
