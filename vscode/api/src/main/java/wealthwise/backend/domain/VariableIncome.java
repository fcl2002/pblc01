package wealthwise.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@DiscriminatorValue("VARIABLE_INCOME")
@EqualsAndHashCode(callSuper = true)

public class VariableIncome extends Asset {
    
    @NotBlank
    @Column(nullable = false, unique = true)
    private String ticker;
    
    private Boolean is_etf;

    public VariableIncome(@NotBlank String name, Double invested_value, Integer number_of_quotas, Double current_value) {
        super(name, invested_value, number_of_quotas, current_value, AssetRole.VARIABLE_INCOME);
    }
}
