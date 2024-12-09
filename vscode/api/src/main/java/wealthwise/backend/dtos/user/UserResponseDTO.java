package wealthwise.backend.dtos.user;

import wealthwise.backend.domain.user.User;
import wealthwise.backend.domain.user.UserRole;

public record UserResponseDTO(String username, String email, String risk_profile, UserRole role) {
    public UserResponseDTO(User user) {
        this(user.getUsername(), user.getEmail(), user.getRisk_profile(), user.getRole());
    }
}
