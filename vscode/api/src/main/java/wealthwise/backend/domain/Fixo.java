package wealthwise.backend.domain;

import jakarta.persistence.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)

public class Fixo extends Ativo {
    private double profitability;
    private int period;
    private boolean is_taxable; // imposto de renda
    private int face_value;
}