package wealthwise.backend.controller;

import java.util.List;

import wealthwise.backend.domain.user.AuthenticationDTO;
import wealthwise.backend.domain.user.LoginResponseDTO;
import wealthwise.backend.domain.user.RegisterDTO;
import wealthwise.backend.domain.user.User;
import wealthwise.backend.infrastructure.security.TokenService;
import wealthwise.backend.repositories.UserRepository;
import wealthwise.backend.services.AuthorizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.repository.findByUsername(data.username()) != null)
            return ResponseEntity.badRequest().build();
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User  newUser = new User(data.email(), data.username(), encryptedPassword, data.risk_profile(), data.role());
        
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
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
