package wealthwise.backend.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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
    @Id
    @GeneratedValue
    private UUID id;
    
    private double profitability;
    private int period;
    private boolean is_taxable; // imposto de renda
    private int face_value;
}