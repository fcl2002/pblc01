package wealthwise.backend.dtos.asset;

import wealthwise.backend.domain.VariableIncome;

public record VariableResponseDTO(String name, Double invested_value, Integer number_of_quotas, Double current_value, String ticker, Boolean is_etf) {    
    public VariableResponseDTO(VariableIncome variable) {
        this(
            variable.getName(),
            variable.getInvested_value(),
            variable.getNumber_of_quotas(),
            variable.getCurrent_value(),
            variable.getTicker(),
            variable.getIs_etf()
        );
    }
}
