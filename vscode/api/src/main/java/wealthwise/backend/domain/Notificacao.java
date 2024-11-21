package wealthwise.backend.domain;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "notificacao")
@DynamicUpdate

public class Notificacao {
    @Id
    @GeneratedValue
    private Long id;

    private String notification_message;
    private String message_type;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Usuario usuario;
    
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Cotacao cotacao;
}
