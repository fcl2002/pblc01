package wealthwise.backend.repositories;

import wealthwise.backend.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, String> {
    boolean existsById(String username);
}
