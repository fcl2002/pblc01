package wealthwise.backend.services;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.user.User;
import wealthwise.backend.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User getUserById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id - " + id));
    }

    private String getIdFromEntity(User user) {
        try {
            Field idField = user.getClass().getDeclaredField("username");
            idField.setAccessible(true);
            return (String) idField.get(user);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Can't recover object's ID.", e);
        }
    }

    public User createUser(User user) {
        String id = getIdFromEntity(user);
    
        if (id != null && repository.existsById(id))
            throw new IllegalArgumentException("Object already registered.");
    
        return repository.save(user);
    }

    public User updateUser(Map< String, Object> updates, String id) {
        
        User result = repository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        updates.forEach((key, value) -> {
            switch(key) {
                case "email":
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
                    throw new IllegalArgumentException("Field: " + key + "is not valid for update.");
            }
        });

        return repository.save(result);
    }

    public void deleteUser(String userID) {

        User user = getUserById(userID);
        repository.delete(user);
    }
}
