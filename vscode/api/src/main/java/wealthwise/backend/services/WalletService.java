package wealthwise.backend.services;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.User;
import wealthwise.backend.domain.Wallet;
import wealthwise.backend.dtos.wallet.WalletRequestDTO;
import wealthwise.backend.dtos.wallet.WalletResponseDTO;
import wealthwise.backend.exceptions.IllegalFieldException;
import wealthwise.backend.exceptions.ResourceNotFoundException;
import wealthwise.backend.repositories.WalletRepository;

@Service
public class WalletService {
    
    @Autowired
    private WalletRepository walletRepository;
    
    @Autowired
    private UserService userService;

    public Wallet getWalletById(String id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet with id '" + id + "' not found."));
    }

    public WalletResponseDTO createWallet(WalletRequestDTO request) {
    
        if (request.userId() == null || walletRepository.existsByName(request.name()))
            throw new IllegalFieldException("'name'", "is not valid."); 
            
        User user = userService.getUserById(request.userId());

        Wallet carteira = new Wallet();
        carteira.setName(request.name());
        carteira.setUser(user);
        
        List<Wallet> carteiras = user.getCarteiras();
        carteiras.add(carteira);
        user.setCarteiras(carteiras);

        WalletResponseDTO walletDTO = new WalletResponseDTO(walletRepository.save(carteira));

        return walletDTO;
    }

    public WalletResponseDTO updateWallet(Map<String, Object> updates, String id) {
        
        Wallet wallet = getWalletById(id);

        updates.forEach((key, value) -> {
            switch(key) {
                case "name":
                    if ((String) value == null || walletRepository.existsByName((String) value))
                        throw new IllegalFieldException("'name'", "is not valid.");
                    
                    wallet.setName((String) value);
                    break;
                case "risk":
                    if((Double) value < 0.0)
                        throw new IllegalFieldException("'" + (String) key + "'", "cant be lower tahn zero.");

                    wallet.setRisk((Double) value);
                    break;
                default:
                    throw new IllegalFieldException("'" + (String) key + "'", "is not valid for update.");
            }
        });

        WalletResponseDTO walletDTO = new WalletResponseDTO(walletRepository.save(wallet));
        return walletDTO;
    }

    public void deleteWallet(String id) {
       
        User user = userService.getUserById(getWalletById(id).getUser().getId());

        if(Objects.isNull(user))
            throw new ResourceNotFoundException("User was not found.");
        
        List<Wallet> carteiras = user.getCarteiras();
        for(Iterator<Wallet> it = carteiras.iterator(); it.hasNext();) {
            Wallet carteira = it.next();
            if(carteira.getId().equals(id)){
                carteira.setUser(null);
                it.remove();
                break;
            }
        }
        
        walletRepository.deleteById(id);
    }
}
