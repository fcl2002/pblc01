package wealthwise.backend.repositories;

import wealthwise.backend.domain.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {    
}
