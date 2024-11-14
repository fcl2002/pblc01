package wealthwise.backend.repositories;

import wealthwise.backend.domain.Variavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariavelRepository extends JpaRepository<Variavel, Long> {    
}