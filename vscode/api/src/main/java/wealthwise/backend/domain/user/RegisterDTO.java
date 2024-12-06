package wealthwise.backend.domain.user;

public record RegisterDTO(String email, String username, String password, String risk_profile, UserRole role) {
}
