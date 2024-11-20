package wealthwise.backend.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Ativo;
import wealthwise.backend.domain.Carteira;
import wealthwise.backend.domain.Fixo;
import wealthwise.backend.repositories.FixoRepository;

@Service
public class FixoService extends BaseService <Fixo, Long, FixoRepository> {

    @Autowired
    private FixoRepository fixoRepository;

    @Autowired
    private CarteiraService carteiraService;

    public Fixo getFixoById(Long id) {
        return fixoRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Fixo not found with id - " + id));
    }

    private Long getIdFromEntity(Fixo fixo){
        try{
            Field idField = fixo.getClass().getSuperclass().getDeclaredField("id");
            idField.setAccessible(true);
            return (Long) idField.get(fixo);            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Can't recover object's ID.", e);
        }
    }

    public Fixo createFixo(Fixo fixo) {
        Long id = getIdFromEntity(fixo);
    
        if (id != null && fixoRepository.existsById(id))
            throw new IllegalArgumentException("Object already registered.");

        Carteira carteira = carteiraService.getCarteiraById(fixo.getCarteira().getId());
        
        List<Ativo> ativos = carteira.getAtivos();
        ativos.add(fixo);
        carteira.setAtivos(ativos);
        
        return fixoRepository.save(fixo);
    }


    public Fixo updateFixo(Fixo updatedFixo, Long fixoID) {
        
        Optional<Fixo> result = fixoRepository.findById(fixoID);

        if(result.isPresent()) {
            Fixo existingFixo = getFixoById(fixoID);
    
            if (updatedFixo.getName() != null)
                existingFixo.setName(updatedFixo.getName());
            if (updatedFixo.getInvested_value() != null)
                existingFixo.setInvested_value(updatedFixo.getInvested_value());
            if (updatedFixo.getNumber_of_quotas() != null)
                existingFixo.setNumber_of_quotas(updatedFixo.getNumber_of_quotas());
            if (updatedFixo.getCurrent_value() != null)
                existingFixo.setCurrent_value(updatedFixo.getCurrent_value());

            if (updatedFixo.getProfitability() != null)
                existingFixo.setProfitability(updatedFixo.getProfitability());
            if (updatedFixo.getPeriod() != null)
                existingFixo.setPeriod(updatedFixo.getPeriod());
            if (updatedFixo.getIs_taxable() != null)
                existingFixo.setIs_taxable(updatedFixo.getIs_taxable());
            if (updatedFixo.getFace_value() != null)
                existingFixo.setFace_value(updatedFixo.getFace_value());
    
            return fixoRepository.save(existingFixo);

        } else
            throw new IllegalArgumentException("Fixo's id does not exist - " + updatedFixo.getId());
    }

    public void deleteId(Long id) {
        fixoRepository.deleteById(id);
    }
}