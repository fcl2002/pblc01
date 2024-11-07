package wealthwise.backend.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;

@Data
@Entity
@Getter
@Setter

public abstract class Ativo {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private double invested_value;
    private int number_of_quotas;
    private double current_value;

    @ManyToOne
    private Carteira carteira;
    @OneToMany
    private List<Cotacao> cotacoes;
}
