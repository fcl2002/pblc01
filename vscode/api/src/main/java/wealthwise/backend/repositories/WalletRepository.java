package wealthwise.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import wealthwise.backend.domain.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, String> {
    Boolean existsByName(String name);
}
