package wealthwise.backend.repositories;

import java.util.UUID;
import wealthwise.backend.domain.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtivoRepository extends JpaRepository<Ativo, UUID> {    
}

