package wealthwise.backend.domain.user;

public record UserResponseDTO(String username, String email, String risk_profile, UserRole role) {
    public UserResponseDTO(User user) {
        this(user.getUsername(), user.getEmail(), user.getRisk_profile(), user.getRole());
    }
}
