package wealthwise.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import wealthwise.backend.domain.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, String> {
    UserDetails findByUsername(String username);
}
