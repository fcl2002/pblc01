package wealthwise.backend.services;


import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Cotacao;
import wealthwise.backend.repositories.CotacaoRepository;

@Service
public class CotacaoService extends BaseService <Cotacao, Long, CotacaoRepository> {

}
