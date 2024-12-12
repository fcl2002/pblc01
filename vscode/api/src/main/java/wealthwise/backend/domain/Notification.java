package wealthwise.backend.domain;

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
@Table(name = "notifications")

public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String message;
    // private String message_type;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JsonBackReference
    private User user;
}
