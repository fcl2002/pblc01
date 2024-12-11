package wealthwise.backend.domain;

// import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carteiras")

public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Wallet must have a name")
    private String name;

    private Double risk = 0.0;

    @JsonBackReference
    @JoinColumn(name = "usuario_id")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private User user;
    
    // @OneToMany(cascade = CascadeType.REMOVE)
    // @JsonManagedReference
    // private List<Ativo> ativos;
}
