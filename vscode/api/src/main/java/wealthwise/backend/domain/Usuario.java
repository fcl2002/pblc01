package wealthwise.backend.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Data
@Entity
@Getter
@Setter
@Table(name = "usuario")

public class Usuario {
    @Id
    private String username;

    private String email;
    private String userpass;
    private boolean is_active;
    private boolean is_super;
    private String risk_profile;

    @OneToMany
    private List<Carteira> carteiras;
    @OneToMany
    private List<Notificacao> notificacoes;
}
