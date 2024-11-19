package wealthwise.backend.domain;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@Entity
@Getter
@Setter
@DynamicUpdate
@Table(name = "carteira")

public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Wallet must have a name")
    private String name;
    private double risk;

    @ManyToOne
    private Usuario usuario;
    @OneToMany
    private List<Ativo> ativos;
}
