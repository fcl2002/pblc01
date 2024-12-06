package wealthwise.backend.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wealthwise.backend.domain.user.User;
import wealthwise.backend.domain.user.UserResponseDTO;
import wealthwise.backend.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<UserResponseDTO> userList = this.userRepository.findAll().stream().map(UserResponseDTO::new).toList();
        
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String id) {
        User user = this.userRepository.findById(id)
                     .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
        UserResponseDTO userDTO = new UserResponseDTO(user);

        return ResponseEntity.ok(userDTO);
    }

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
