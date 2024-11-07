package wealthwise.backend.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;

@Data
@Entity
@Getter
@Setter
@Table(name = "usuario")

public class Usuario {
    @Id
    @GeneratedValue
    private UUID id;

    private String email;
    private String username;
    private String userpass;
    private boolean is_active;
    private boolean is_super;
    private String risk_profile;

    @OneToMany
    private List<Carteira> carteiras;
    @OneToMany
    private List<Notificacao> notificacoes;
}
