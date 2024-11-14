package wealthwise.backend.domain;

import org.hibernate.annotations.DynamicUpdate;

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
@DynamicUpdate

public class Notificacao {
    @Id
    @GeneratedValue
    private Long id;

    private String notification_message;
    private String message_type;

    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Cotacao cotacao;
}
