package wealthwise.backend.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.user.User;
import wealthwise.backend.dtos.user.UserResponseDTO;
import wealthwise.backend.exceptions.ResourceNotFoundException;
import wealthwise.backend.exceptions.IllegalFieldException;
import wealthwise.backend.exceptions.ObjectAlreadyRegisteredException;
import wealthwise.backend.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "User", 
                    "User with ID '" + id + "' not found."));
    }

    public UserResponseDTO updateUser(Map< String, Object> updates, String id) {
        
        User result = getUserById(id);

        updates.forEach((key, value) -> {
            switch(key) {
                case "email":
                    if (userRepository.existsByEmail((String) value))
                        throw new ObjectAlreadyRegisteredException("User with email '" + (String) value + "' is already registered.");
                        
                    result.setEmail((String) value);
                    break;
                case "username":
                    result.setUsername((String) value);
                    break;
                case "password":
                    String encryptedPassword = new BCryptPasswordEncoder().encode((String) value);
                    result.setPassword(encryptedPassword);
                    break;
                case "risk_profile":
                    result.setRisk_profile((String) value);
                    break;
                default:
                    throw new IllegalFieldException("'" + (String) key + "'", "is not valid for update.");
            }
        });

        UserResponseDTO userDTO = new UserResponseDTO(userRepository.save(result));
        return userDTO;
    }

    public void deleteUser(String userID) {
        User user = getUserById(userID);
        userRepository.delete(user);
    }
}
