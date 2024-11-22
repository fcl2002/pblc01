package wealthwise.backend.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type" // JSON will include a field "type" to identify the subclass
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Fixo.class, name = "fixo"),
    @JsonSubTypes.Type(value = Variavel.class, name = "variavel")
})

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

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonBackReference
    private Carteira carteira;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Cotacao> cotacoes;
}
