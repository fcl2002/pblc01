package wealthwise.backend.dtos.user;

import java.util.List;

import wealthwise.backend.domain.User;
import wealthwise.backend.domain.UserRole;
import wealthwise.backend.dtos.wallet.WalletDTO;

public record UserResponseDTO(String username, String email, String risk_profile, UserRole role, List<WalletDTO> wallets) {
    public UserResponseDTO(User user) {
        this(
            user.getUsername(), 
            user.getEmail(), 
            user.getRisk_profile(), 
            user.getRole(), 
            user.getCarteiras().stream().map(WalletDTO::new).toList()
        );
    }
}
