package wealthwise.backend.dtos.user;

import wealthwise.backend.domain.UserRole;

public record RegisterDTO(String email, String username, String password, String risk_profile, UserRole role) {
}
