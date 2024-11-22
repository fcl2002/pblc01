package wealthwise.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Ativo;
import wealthwise.backend.repositories.AtivoRepository;

@Service("ativoService")
public class AtivoService extends BaseService <Ativo, Long, AtivoRepository> {
    @Autowired
    private AtivoRepository ativoRepository;

    public Ativo getAtivoById(Long ativoID) {

        return ativoRepository.findById(ativoID)
                .orElseThrow(() -> new IllegalArgumentException("Ativo not found with id - " + ativoID));
    }
}
