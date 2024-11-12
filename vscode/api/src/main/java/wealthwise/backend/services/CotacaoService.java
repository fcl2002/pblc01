package wealthwise.backend.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Cotacao;
import wealthwise.backend.repositories.CotacaoRepository;

@Service
public class CotacaoService extends BaseService <Cotacao, UUID, CotacaoRepository> {

}
