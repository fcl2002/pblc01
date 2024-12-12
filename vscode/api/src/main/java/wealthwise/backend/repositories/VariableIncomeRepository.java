package wealthwise.backend.repositories;

import wealthwise.backend.domain.VariableIncome;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VariableIncomeRepository extends JpaRepository<VariableIncome, String> {
    Boolean existsByName(String name);
    Boolean existsByTicker(String ticker);
    VariableIncome findByName(String name);
    Optional<VariableIncome> findByTicker(String ticker);
}
