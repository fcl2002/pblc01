package wealthwise.backend.dtos.asset;

public record VariableRequestDTO(String name, Double invested_value, Integer number_of_quotas, Double current_value, String ticker, String walletId) {    
}
