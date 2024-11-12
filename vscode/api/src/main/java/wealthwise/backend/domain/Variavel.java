package wealthwise.backend.domain;

import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    @Id
    @GeneratedValue
    private UUID id;

    private String ticker;
    private boolean is_etf;
}