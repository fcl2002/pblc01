package wealthwise.backend.services;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.lang.reflect.Field;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import wealthwise.backend.domain.Ativo;
import wealthwise.backend.domain.Carteira;
import wealthwise.backend.domain.Notificacao;
import wealthwise.backend.domain.Variavel;
import wealthwise.backend.repositories.VariavelRepository;

@Service
public class VariavelService extends BaseService <Variavel, Long, VariavelRepository> {
    @Autowired
    private VariavelRepository variavelRepository;

    @Autowired
    private CarteiraService carteiraService;

    public Variavel getVariavelById(Long id) {
        return variavelRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Variavel not found with id - " + id));
    }

    private Long getIdFromEntity(Variavel variavel){
        try{
            Field idField = variavel.getClass().getSuperclass().getDeclaredField("id");
            idField.setAccessible(true);
            return (Long) idField.get(variavel);            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Can't recover object's ID.", e);
        }
    }

    public Variavel createVariavel(Variavel variavel) {
        Long id = getIdFromEntity(variavel);
    
        if (id != null && variavelRepository.existsById(id))
            throw new IllegalArgumentException("Object already registered.");

        Carteira carteira = carteiraService.getCarteiraById(variavel.getCarteira().getId());
        
        List<Ativo> ativos = carteira.getAtivos();
        ativos.add(variavel);
        carteira.setAtivos(ativos);

        return variavelRepository.save(variavel);
    }


    public Variavel updateVariavel(Variavel updatedVariavel, Long fixoID) {
        
        
        Optional<Variavel> result = variavelRepository.findById(fixoID);

        if(result.isPresent()) {
            Variavel existingVariavel = getVariavelById(fixoID);
    
            if (updatedVariavel.getName() != null)
                existingVariavel.setName(updatedVariavel.getName());
            if (updatedVariavel.getInvested_value() != null)
                existingVariavel.setInvested_value(updatedVariavel.getInvested_value());
            if (updatedVariavel.getNumber_of_quotas() != null)
                existingVariavel.setNumber_of_quotas(updatedVariavel.getNumber_of_quotas());
            if (updatedVariavel.getCurrent_value() != null)
                existingVariavel.setCurrent_value(updatedVariavel.getCurrent_value());

            if (updatedVariavel.getTicker() != null)
                existingVariavel.setTicker(updatedVariavel.getTicker());
            if (updatedVariavel.getIs_etf() != null)
                existingVariavel.setIs_etf(updatedVariavel.getIs_etf());
    
            return variavelRepository.save(existingVariavel);

        } else
            throw new IllegalArgumentException("Variavel's id does not exist - " + updatedVariavel.getId());
    }

    public void deleteVariavel(Long id) {
        Carteira carteira = carteiraService.getCarteiraById(getVariavelById(id).getCarteira().getId());
        List<Notificacao> notificacoes = getVariavelById(id).getCarteira().getUsuario().getNotificacoes();
        
        if(Objects.nonNull(carteira)){
            List<Ativo> ativos = carteira.getAtivos();

            for(Iterator<Ativo> it = ativos.iterator(); it.hasNext();) {
                Ativo ativo = it.next();
                if(ativo.getId() == id){
                    ativo.setCarteira(null);
                    it.remove();
                    break;
                }
            }
        }

        if(Objects.nonNull(notificacoes)){
            for(Iterator<Notificacao> it = notificacoes.iterator(); it.hasNext();) {
                Notificacao notificacao = it.next();
                if(notificacao.getCotacao().getId() == id){
                    notificacao.setCotacao(null);
                    it.remove();
                }
            }
        }

        variavelRepository.deleteById(id);
    }
}
