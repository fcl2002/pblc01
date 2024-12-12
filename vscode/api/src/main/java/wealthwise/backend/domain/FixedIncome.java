package wealthwise.backend.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@DiscriminatorValue("FIXED_INCOME")
@EqualsAndHashCode(callSuper = true)

public class FixedIncome extends Asset {
    private Double profitability;
    private Integer period;
    private Boolean is_taxable; // imposto de renda
    private Integer face_value;

    public FixedIncome(@NotBlank String name, Double invested_value, Integer number_of_quotas, Double current_value) {
        super(name, invested_value, number_of_quotas, current_value, AssetRole.FIXED_INCOME);
    }
}
