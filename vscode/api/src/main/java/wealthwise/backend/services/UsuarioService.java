package wealthwise.backend.services;

import java.util.Optional;
import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Usuario;
import wealthwise.backend.repositories.UsuarioRepository;

@Service
public class UsuarioService extends BaseService <Usuario, String, UsuarioRepository> {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUserById(String userID) {

        return usuarioRepository.findById(userID)
                .orElseThrow(() -> new IllegalArgumentException("Barber not found with id - " + userID));
    }

    private String getIdFromEntity(Usuario user) {
        try {
            Field idField = user.getClass().getDeclaredField("username");
            idField.setAccessible(true);
            return (String) idField.get(user);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Erro ao recuperar ID do objeto.", e);
        }
    }

    public Usuario createUser(Usuario user) {
        String id = getIdFromEntity(user);
    
        if (id != null && usuarioRepository.existsById(id))
            throw new IllegalArgumentException("Objeto já existe e não pode ser criado novamente.");
    
        return usuarioRepository.save(user);
    }

    public Usuario updateUser(Usuario updatedUser, String userID) {
        
        Optional<Usuario> result = usuarioRepository.findById(userID);

        if(result.isPresent()) {
            Usuario existingUser = getUserById(updatedUser.getUsername());
    
            if (updatedUser.getEmail() != null)
                existingUser.setEmail(updatedUser.getEmail());
            if (updatedUser.getPassword() != null)
                existingUser.setPassword(updatedUser.getPassword());
            if (updatedUser.getRisk_profile() != null)
                existingUser.setRisk_profile(updatedUser.getRisk_profile());
    
            return usuarioRepository.save(existingUser);

        } else
            throw new IllegalArgumentException("Username does not exist - " + updatedUser.getUsername());
    }

    public void deleteUser(String userID) {

        Usuario user = getUserById(userID); // Will throw exception if not found
        usuarioRepository.delete(user);
    }
}
