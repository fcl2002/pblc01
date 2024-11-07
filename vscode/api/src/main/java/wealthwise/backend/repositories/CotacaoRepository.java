package wealthwise.backend.repositories;

import java.util.UUID;
import wealthwise.backend.domain.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotacaoRepository extends JpaRepository<Cotacao, UUID> {    
}
