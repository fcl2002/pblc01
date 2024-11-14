package wealthwise.backend.domain;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)

public class Variavel extends Ativo {
    private String ticker;
    private boolean is_etf;
}