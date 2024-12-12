package wealthwise.backend.services;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Asset;
import wealthwise.backend.domain.AssetRole;
import wealthwise.backend.domain.FixedIncome;
import wealthwise.backend.domain.Wallet;
import wealthwise.backend.dtos.asset.FixedRequestDTO;
import wealthwise.backend.dtos.asset.FixedResponseDTO;
import wealthwise.backend.exceptions.IllegalFieldException;
import wealthwise.backend.exceptions.ResourceNotFoundException;
import wealthwise.backend.repositories.FixedIncomeRepository;

@Service
public class FixedIncomeService {
    
    @Autowired
    private FixedIncomeRepository repository;

    @Autowired
    private WalletService service;

    public FixedIncome getFixedById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fixed Income with id '" + id + "' not found."));
    }

    public FixedResponseDTO createFixedIncome(FixedRequestDTO request) {

        if(request.name() == null || repository.existsByName(request.name()))
            throw new IllegalFieldException("Fixed 'ticker'", "is not valid.");

        Wallet wallet = service.getWalletById(request.walletId());

        FixedIncome fixed = new FixedIncome();
        fixed.setName(request.name());
        fixed.setInvested_value(request.invested_value());
        fixed.setProfitability(request.profitability());
        fixed.setPeriod(request.period());
        fixed.setIs_taxable(request.is_taxable());
        fixed.setRole(AssetRole.FIXED_INCOME);
        fixed.setWallet(wallet);
        
        List<Asset> assets = wallet.getAssets();
        assets.add(fixed);
        wallet.setAssets(assets);

        FixedResponseDTO fixedDTO = new FixedResponseDTO(repository.save(fixed));

        return fixedDTO;
    }


    public FixedResponseDTO updateFixed(Map<String, Object> updates, String id) {
        
        FixedIncome fixed = getFixedById(id);

        updates.forEach((key, value) -> {
            switch(key) {
                case "name":
                    if ((String) value == null || repository.existsByName((String) value))
                        throw new IllegalFieldException("Fixed 'name'", "is not valid.");
                    
                    fixed.setName((String) value);
                    break;
                case "invested_value":
                    if((Double) value < 0.0)
                        throw new IllegalFieldException("'" + (String) key + "'", "cant be lower than zero.");

                    fixed.setInvested_value((Double) value);
                    break;
                case "profitability":
                    if((Double) value < 0.0)
                        throw new IllegalFieldException("'" + (String) key + "'", "cant be lower than zero.");

                    fixed.setProfitability((Double) value);
                    break;
                case "period":
                    if((Integer) value < 0)
                        throw new IllegalFieldException("'" + (String) key + "'", "cant be lower than zero.");

                    fixed.setPeriod((Integer) value);
                    break;
                case "is_taxable":
                    fixed.setIs_taxable((Boolean) value);
                    break;
                default:
                    throw new IllegalFieldException("'" + (String) key + "'", "is not valid for update.");
            }
        });

        FixedResponseDTO fixedDTO = new FixedResponseDTO(repository.save(fixed));
        return fixedDTO;
    }

    public void deleteFixed(String id) {
        Wallet wallet = service.getWalletById(getFixedById(id).getWallet().getId());
        
        if(Objects.isNull(wallet))
            throw new ResourceNotFoundException("Wallet was not found.");
    
        List<Asset> ativos = wallet.getAssets();
        for(Iterator<Asset> it = ativos.iterator(); it.hasNext();) {
            Asset ativo = it.next();
            if(ativo.getId() == id) {
                ativo.setWallet(null);
                it.remove();
                break;
            }
        }    
        
        repository.deleteById(id);
    }
}
