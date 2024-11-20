package wealthwise.backend.repositories;

import wealthwise.backend.domain.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> { 
}