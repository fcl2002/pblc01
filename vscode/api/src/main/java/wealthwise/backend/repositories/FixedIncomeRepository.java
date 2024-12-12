package wealthwise.backend.repositories;

import wealthwise.backend.domain.FixedIncome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedIncomeRepository extends JpaRepository<FixedIncome, String> {

}
