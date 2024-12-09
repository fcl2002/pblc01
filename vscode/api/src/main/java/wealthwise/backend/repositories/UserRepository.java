package wealthwise.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import wealthwise.backend.domain.user.User;

public interface UserRepository extends JpaRepository <User, String> {
    UserDetails findByUsername(String username);
    Boolean existsByEmail(String email);
}
