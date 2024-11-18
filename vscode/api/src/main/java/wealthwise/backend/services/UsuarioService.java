package wealthwise.backend.services;

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

    public void deleteUser(String userID) {

        Usuario user = getUserById(userID); // Will throw exception if not found
        usuarioRepository.delete(user);
    }
}
