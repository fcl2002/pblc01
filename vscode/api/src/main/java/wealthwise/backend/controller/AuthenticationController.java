package wealthwise.backend.controller;

import java.util.List;

import wealthwise.backend.domain.usuario.AuthenticationDTO;
import wealthwise.backend.domain.usuario.LoginResponseDTO;
import wealthwise.backend.domain.usuario.Usuario;
import wealthwise.backend.repositories.UsuarioRepository;
import wealthwise.backend.services.AuthorizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    // @GetMapping("/all")
    // public ResponseEntity<List<Usuario>> getAllUsers() {
    //     List<Usuario> users = usuarioService.getAll();
    //     return ResponseEntity.ok(users);
    // }

    // @GetMapping("/{username}")
    // public ResponseEntity<Usuario> getUser(@PathVariable String username) {
    //     Usuario user = usuarioService.getId(username).orElse(null);
    //     return ResponseEntity.ok(user);
    // }

    // @PutMapping("/{username}")
    // public ResponseEntity<Usuario> updateUser(@RequestBody Usuario usuario, @PathVariable String username) {
    //     Usuario user = usuarioService.updateUser(usuario, username);
    //     return ResponseEntity.ok(user);
    // }

    // @DeleteMapping("/{username}")
    // public ResponseEntity<String> deleteUser(@PathVariable String username) {
    //     usuarioService.deleteUser(username);
    //     return ResponseEntity.ok("User deleted (id: " + username + ")");
    // }
}
