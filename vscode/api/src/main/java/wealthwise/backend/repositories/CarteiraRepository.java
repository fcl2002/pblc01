package wealthwise.backend.repositories;

import java.util.UUID;
import wealthwise.backend.domain.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, UUID> {    
}
