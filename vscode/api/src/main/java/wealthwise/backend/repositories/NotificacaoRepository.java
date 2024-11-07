package wealthwise.backend.repositories;

import java.util.UUID;
import wealthwise.backend.domain.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, UUID> {    
}
