package wealthwise.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "asset_type", discriminatorType = DiscriminatorType.STRING)

public abstract class Asset {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    private Double invested_value;

    private Integer number_of_quotas;

    private Double current_value;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetRole role;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonBackReference
    private Wallet wallet;

    // @OneToMany(cascade = CascadeType.REMOVE)
    // @JsonManagedReference
    // private List<Cotacao> cotacoes;

    public Asset(String name, Double invested_value, Integer number_of_quotas, Double current_value, AssetRole role) {
        this.name = name;
        this.invested_value = invested_value;
        this.number_of_quotas = number_of_quotas;
        this.current_value = current_value;
        this.role = role;
    }
}
