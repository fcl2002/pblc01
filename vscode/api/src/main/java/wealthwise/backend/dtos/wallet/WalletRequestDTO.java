package wealthwise.backend.dtos.wallet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WalletRequestDTO(
    @NotBlank
    String name, 
    
    @NotNull
    String userId) {
}
