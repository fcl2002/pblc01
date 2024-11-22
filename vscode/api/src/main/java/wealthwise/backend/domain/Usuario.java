package wealthwise.backend.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
@Entity
@Getter
@Setter
@Table(name = "usuario")

public class Usuario {
    @Id
    private String username;
    
    @Email
    @Column(nullable = false, unique = true, length = 255)
    @NotBlank(message = "Email cannot be null and must be unique")
    private String email;
    
    @Column(nullable = false)
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    private String risk_profile;

    // Admin
    private boolean is_super;
    private boolean is_active;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Carteira> carteiras;

}
