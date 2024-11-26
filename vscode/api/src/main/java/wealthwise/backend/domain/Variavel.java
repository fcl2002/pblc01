package wealthwise.backend.domain;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)

public class Variavel extends Ativo {
    @NotBlank(message = "Ticker can't be blank")
    @NotNull(message = "Ticker can't be null")
    private String ticker;
    private Boolean is_etf;
}