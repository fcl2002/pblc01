package wealthwise.backend.services;

import java.lang.reflect.Field;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public User updateUser(User updatedUser, String id) {
        
        Optional<User> result = repository.findById(id);

        if(result.isPresent()) {
            User existingUser = getUserById(id);
    
            if (updatedUser.getEmail() != null)
                existingUser.setEmail(updatedUser.getEmail());
            if (updatedUser.getPassword() != null)
                existingUser.setPassword(updatedUser.getPassword());
            if (updatedUser.getRisk_profile() != null)
                existingUser.setRisk_profile(updatedUser.getRisk_profile());
    
            return repository.save(existingUser);

        } else
            throw new IllegalArgumentException("Username does not exist - " + updatedUser.getUsername());
    }

    public void deleteUser(String userID) {

        User user = getUserById(userID);
        repository.delete(user);
    }
}
