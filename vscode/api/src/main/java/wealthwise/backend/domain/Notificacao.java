package wealthwise.backend.domain;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@DynamicUpdate
@Table(name = "notificacao")

public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String notification_message;
    private String message_type;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonBackReference
    private Usuario usuario;
    
    @ManyToOne
    private Cotacao cotacao;
}
