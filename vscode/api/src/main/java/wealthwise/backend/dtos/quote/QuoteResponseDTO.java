package wealthwise.backend.dtos.quote;

import java.util.Date;

import wealthwise.backend.domain.Quote;

public record QuoteResponseDTO(Double value, Date date) {

    public QuoteResponseDTO(Quote quote) {
        this(
            quote.getValue(),
            quote.getDate()
        );
    } 
}
