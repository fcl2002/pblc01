package wealthwise.backend.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wealthwise.backend.domain.user.User;
import wealthwise.backend.domain.user.UserResponseDTO;
import wealthwise.backend.repositories.UserRepository;
import wealthwise.backend.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

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

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody Map<String, Object> updates, @PathVariable String id) {
        try {
            User user = userService.updateUser(updates, id);
            UserResponseDTO userDTO = new UserResponseDTO(user);
            return ResponseEntity.ok(userDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted with id: " + id);
    }
}
