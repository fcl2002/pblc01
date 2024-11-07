package wealthwise.backend.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "notificacao")

public class Notificacao {
    @Id
    @GeneratedValue
    private UUID id;

    private String notification_message;
    private String message_type;

    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Cotacao cotacao;
}
