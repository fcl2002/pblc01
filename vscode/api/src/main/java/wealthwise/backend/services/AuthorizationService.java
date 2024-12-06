package wealthwise.backend.services;

import java.util.Optional;
import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.usuario.Usuario;
import wealthwise.backend.repositories.UsuarioRepository;

@Service
public class AuthorizationService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Usuario getUserById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id - " + id));
    }

    private String getIdFromEntity(Usuario user) {
        try {
            Field idField = user.getClass().getDeclaredField("username");
            idField.setAccessible(true);
            return (String) idField.get(user);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Can't recover object's ID.", e);
        }
    }

    public Usuario createUser(Usuario user) {
        String id = getIdFromEntity(user);
    
        if (id != null && repository.existsById(id))
            throw new IllegalArgumentException("Object already registered.");
    
        return repository.save(user);
    }

    public Usuario updateUser(Usuario updatedUser, String id) {
        
        Optional<Usuario> result = repository.findById(id);

        if(result.isPresent()) {
            Usuario existingUser = getUserById(id);
    
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

        Usuario user = getUserById(userID);
        repository.delete(user);
    }
}
