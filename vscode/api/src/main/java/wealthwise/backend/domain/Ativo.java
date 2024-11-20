package wealthwise.backend.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@Entity
@Getter
@Setter
@DynamicUpdate

public abstract class Ativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name can't be blank")
    private String name;
    private Double invested_value;
    private Integer number_of_quotas;
    private Double current_value;

    @ManyToOne
    @JsonBackReference
    private Carteira carteira;
    @OneToMany
    private List<Cotacao> cotacoes;
}
