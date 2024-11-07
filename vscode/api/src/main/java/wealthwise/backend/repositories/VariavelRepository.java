package wealthwise.backend.repositories;

import java.util.UUID;
import wealthwise.backend.domain.Variavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariavelRepository extends JpaRepository<Variavel, UUID> {    
}