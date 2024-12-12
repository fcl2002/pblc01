package wealthwise.backend.dtos.quote;

import java.util.Date;

public record QuoteRequestDTO(Double value, Date date, String assetId) {
}
