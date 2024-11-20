package wealthwise.backend.services;

import java.lang.reflect.Field;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Carteira;
import wealthwise.backend.repositories.CarteiraRepository;

@Service
public class CarteiraService extends BaseService<Carteira, Long, CarteiraRepository> {

    @Autowired
    private CarteiraRepository carteiraRepository;



    public Carteira getUserById(Long carteiraID) {
        return carteiraRepository.findById(carteiraID)
                .orElseThrow(() -> new IllegalArgumentException("Carteira not found with id - " + carteiraID));
    }


    private Long getIdFromEntity(Carteira carteira) {
        try {
            Field idField = carteira.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            return (Long) idField.get(carteira);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Can't recover object's ID.", e);
        }
    }

    public Carteira createCarteira(Carteira carteira) {
        Long id = getIdFromEntity(carteira);
    
        if (id != null && carteiraRepository.existsById(id))
            throw new IllegalArgumentException("Object already registered.");        

        return carteiraRepository.save(carteira);
    }

    public Carteira updateCarteira(Carteira updatedCarteira, Long carteiraID){
        Optional<Carteira> result = carteiraRepository.findById(carteiraID);
        
        if(result.isPresent()){
            Carteira existingCarteira = getUserById(carteiraID);

            if(updatedCarteira.getName() != null)
                existingCarteira.setName(updatedCarteira.getName());
            if (updatedCarteira.getRisk() != null)
                existingCarteira.setRisk(updatedCarteira.getRisk());

            return carteiraRepository.save(existingCarteira);
        } else
            throw new IllegalArgumentException("Carteira does not exist - " + updatedCarteira.getId());
    }

    public void deleteId(Long id) {
        carteiraRepository.deleteById(id);
    }
}


