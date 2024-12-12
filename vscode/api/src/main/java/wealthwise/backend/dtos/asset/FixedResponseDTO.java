package wealthwise.backend.dtos.asset;

import wealthwise.backend.domain.FixedIncome;

public record FixedResponseDTO(String name, Double invested_value, Double profitability, 
                                Integer period, Boolean is_taxable) {
    
    public FixedResponseDTO(FixedIncome fixed) {
        this(
            fixed.getName(),
            fixed.getInvested_value(),
            fixed.getProfitability(),
            fixed.getPeriod(),
            fixed.getIs_taxable()
        );
    }
}
