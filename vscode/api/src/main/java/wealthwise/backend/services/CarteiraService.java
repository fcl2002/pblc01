package wealthwise.backend.services;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Carteira;
import wealthwise.backend.domain.Usuario;
import wealthwise.backend.repositories.CarteiraRepository;

@Service
public class CarteiraService extends BaseService<Carteira, Long, CarteiraRepository> {

    @Autowired
    private CarteiraRepository carteiraRepository;
    
    @Autowired
    private UsuarioService usuarioService;

    public Carteira getCarteiraById(Long carteiraID) {
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
            
        Usuario usuario = usuarioService.getUserById(carteira.getUsuario().getUsername());
        
        List<Carteira> carteiras = usuario.getCarteiras();
        carteiras.add(carteira);
        usuario.setCarteiras(carteiras);


        return carteiraRepository.save(carteira);
    }

    public Carteira updateCarteira(Carteira updatedCarteira, Long carteiraID){
        Optional<Carteira> result = carteiraRepository.findById(carteiraID);
        
        if(result.isPresent()){
            Carteira existingCarteira = getCarteiraById(carteiraID);

            if(updatedCarteira.getName() != null)
                existingCarteira.setName(updatedCarteira.getName());
            if (updatedCarteira.getRisk() != null)
                existingCarteira.setRisk(updatedCarteira.getRisk());

            return carteiraRepository.save(existingCarteira);
        } else
            throw new IllegalArgumentException("Carteira does not exist - " + updatedCarteira.getId());
    }

    public void deleteCarteira(Long id) {
        Usuario usuario = usuarioService.getUserById(getCarteiraById(id).getUsuario().getUsername());
        

        if(Objects.nonNull(usuario)){
            List<Carteira> carteiras = usuario.getCarteiras();

            for(Iterator<Carteira> it = carteiras.iterator(); it.hasNext();) {
                Carteira carteira = it.next();
                if(carteira.getId().equals(id)){
                    carteira.setUsuario(null);
                    it.remove();
                    break;
                }
            }
        }
        carteiraRepository.deleteById(id);
    }
}


