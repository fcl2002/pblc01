package wealthwise.backend.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Notificacao;
import wealthwise.backend.repositories.NotificacaoRepository;

@Service
public class NotificacaoService extends BaseService <Notificacao, UUID, NotificacaoRepository> {
    public NotificacaoService(NotificacaoRepository repository) {
        super(repository);
    }
}
