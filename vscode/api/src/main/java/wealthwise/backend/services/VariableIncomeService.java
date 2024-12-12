package wealthwise.backend.services;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Asset;
import wealthwise.backend.domain.AssetRole;
import wealthwise.backend.domain.VariableIncome;
import wealthwise.backend.domain.Wallet;
import wealthwise.backend.dtos.asset.VariableRequestDTO;
import wealthwise.backend.dtos.asset.VariableResponseDTO;
import wealthwise.backend.exceptions.IllegalFieldException;
import wealthwise.backend.exceptions.ResourceNotFoundException;
import wealthwise.backend.repositories.VariableIncomeRepository;

@Service
public class VariableIncomeService {

    @Autowired
    private VariableIncomeRepository repository;

    @Autowired
    private WalletService service;

    public VariableIncome getVariableById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Variable Income with id '" + id + "' not found."));
    }

    public VariableIncome getVariableByTicker(String ticker) {
        return repository.findByTicker(ticker)
                .orElseThrow(() -> new ResourceNotFoundException("Variable Income with id '" + ticker + "' not found."));
    }

    public VariableResponseDTO createVariableIncome(VariableRequestDTO request) {

        if(request.ticker() == null || repository.existsByTicker(request.ticker()))
            throw new IllegalFieldException("Variable 'ticker'", "is not valid.");

        Wallet wallet = service.getWalletById(request.walletId());

        VariableIncome variable = new VariableIncome();
        variable.setName(request.name());
        variable.setTicker(request.ticker());
        variable.setInvested_value(request.invested_value());
        variable.setNumber_of_quotas(request.number_of_quotas());
        variable.setCurrent_value(request.current_value());
        variable.setRole(AssetRole.VARIABLE_INCOME);
        variable.setWallet(wallet);
        
        List<Asset> assets = wallet.getAssets();
        assets.add(variable);
        wallet.setAssets(assets);

        VariableResponseDTO variableDTO = new VariableResponseDTO(repository.save(variable));

        return variableDTO;
    }


    public VariableResponseDTO updateVariable(Map<String, Object> updates, String id) {
        
        VariableIncome variable = getVariableById(id);

        updates.forEach((key, value) -> {
            switch(key) {
                case "name":
                    if ((String) value == null || repository.existsByName((String) value))
                        throw new IllegalFieldException("Variable 'name'", "is not valid.");
                    
                    variable.setName((String) value);
                    break;
                case "invested_value":
                    if((Double) value < 0.0)
                        throw new IllegalFieldException("'" + (String) key + "'", "cant be lower than zero.");

                    variable.setInvested_value((Double) value);
                    break;
                case "number_of_quotas":
                    if((Integer) value < 0.0)
                        throw new IllegalFieldException("'" + (String) key + "'", "cant be lower than zero.");

                    variable.setNumber_of_quotas((Integer) value);
                    break;
                case "ticker":
                    if ((String) value == null || repository.existsByName((String) value))
                        throw new IllegalFieldException("Variable 'ticker'", "is not valid.");

                    variable.setTicker((String) value);
                    break;
                case "is_etf":
                    if((Double) value < 0.0)
                        throw new IllegalFieldException("'" + (String) key + "'", "cant be lower tahn zero.");

                    variable.setIs_etf((Boolean) value);
                    break;
                default:
                    throw new IllegalFieldException("'" + (String) key + "'", "is not valid for update.");
            }
        });

        VariableResponseDTO variableDTO = new VariableResponseDTO(repository.save(variable));
        return variableDTO;
    }

    public void deleteVariable(String id) {
        Wallet wallet = service.getWalletById(getVariableById(id).getWallet().getId());
        
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
        
    

