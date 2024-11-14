package wealthwise.backend.repositories;

import wealthwise.backend.domain.Fixo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixoRepository extends JpaRepository<Fixo, Long> {    
}
