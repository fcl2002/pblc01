package wealthwise.backend.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quotes")

public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    Double value;

    Date date;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonBackReference
    private Asset asset;
}
