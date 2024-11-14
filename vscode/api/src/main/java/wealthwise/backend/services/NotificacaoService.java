package wealthwise.backend.services;

import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Notificacao;
import wealthwise.backend.repositories.NotificacaoRepository;

@Service
public class NotificacaoService extends BaseService <Notificacao, Long, NotificacaoRepository> {

}
