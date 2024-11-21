package wealthwise.backend.services;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Cotacao;
import wealthwise.backend.repositories.CotacaoRepository;

@Service
public class CotacaoService extends BaseService <Cotacao, Long, CotacaoRepository> {

    @Autowired
    private CotacaoRepository cotacaoRepository;

    public Cotacao getCotacaoById(Long cotacaoID) {
        
        return cotacaoRepository.findById(cotacaoID)
            .orElseThrow(() -> new IllegalArgumentException("Cotacao not found with id - " + cotacaoID));
    }

    private Long getIdFromEntity(Cotacao cotacao) {
        try {
            Field idField = cotacao.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            return (Long) idField.get(cotacao);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Can't recover object's ID.", e);
        }
    }

    public Cotacao createCotacao(Cotacao cotacao) {
        Long id = getIdFromEntity(cotacao);
    
        if (id != null && cotacaoRepository.existsById(id))
            throw new IllegalArgumentException("Object already registered.");
    
        return cotacaoRepository.save(cotacao);
    }

    public void deleteCotacao(Long cotacaoID) {

        Cotacao cotacao = getCotacaoById(cotacaoID);
        cotacaoRepository.delete(cotacao);
    }
}
