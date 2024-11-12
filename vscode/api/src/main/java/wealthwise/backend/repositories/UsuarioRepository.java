package wealthwise.backend.repositories;

import java.util.UUID;
import wealthwise.backend.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, UUID> {
}
