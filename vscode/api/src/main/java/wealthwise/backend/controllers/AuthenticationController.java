package wealthwise.backend.controllers;

import wealthwise.backend.domain.User;
import wealthwise.backend.dtos.user.AuthenticationDTO;
import wealthwise.backend.dtos.user.LoginResponseDTO;
import wealthwise.backend.dtos.user.RegisterDTO;
import wealthwise.backend.infra.security.TokenService;
import wealthwise.backend.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
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
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        System.out.println(data.username());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        // String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        // User user = repository.findByPassword(encryptedPassword).get(0);
        
        // return ResponseEntity.ok(new LoginResponseDTO(user.getId(), data.username(), user.getEmail(), token));
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        if(this.repository.findByUsername(data.username()) != null)
            return ResponseEntity.badRequest().build();

        if(data.password().length() < 8)
            return ResponseEntity.badRequest().build();
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), data.username(), encryptedPassword, data.risk_profile(), data.role());
        
        this.repository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
