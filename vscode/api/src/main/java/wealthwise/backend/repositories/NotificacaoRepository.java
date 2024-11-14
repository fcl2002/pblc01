package wealthwise.backend.repositories;

import wealthwise.backend.domain.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {    
}
