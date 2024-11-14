package wealthwise.backend.domain;

import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@DynamicUpdate
@Table(name = "cotacao")

public class Cotacao {
    @Id
    @GeneratedValue
    private Long id;
    
    private float price;
    private Date date;
    
    @ManyToOne
    private Ativo ativo;
}
