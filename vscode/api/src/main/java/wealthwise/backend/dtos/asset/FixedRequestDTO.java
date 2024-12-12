package wealthwise.backend.dtos.asset;

public record FixedRequestDTO(String name, Double invested_value, Double profitability, 
                                Integer period, Boolean is_taxable, String walletId) {
}
