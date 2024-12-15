package wealthwise.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import wealthwise.backend.domain.User;

public interface UserRepository extends JpaRepository <User, String> {
    List<User> findByPassword(String password);
    UserDetails findByUsername(String username);
    Boolean existsByEmail(String email);
}
