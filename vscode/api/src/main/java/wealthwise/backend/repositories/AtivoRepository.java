package wealthwise.backend.repositories;

import wealthwise.backend.domain.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtivoRepository extends JpaRepository<Ativo, Long> { 
       
}

