package wealthwise.backend.services;

import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Ativo;
import wealthwise.backend.repositories.AtivoRepository;

@Service("ativoService")
public class AtivoService extends BaseService <Ativo, Long, AtivoRepository> {

}
