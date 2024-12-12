package wealthwise.backend.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import wealthwise.backend.domain.Quote;

public interface QuoteRepository extends JpaRepository<Quote, String> {
    Boolean existsByDate(Date date);
    Optional<Quote> findByDate(Date date);
}
