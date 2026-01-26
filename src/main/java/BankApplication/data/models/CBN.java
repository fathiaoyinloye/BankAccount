package BankApplication.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "cbn")
public class CBN {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Add this!
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    /*@OneToMany
    private List<Bank> banks = new ArrayList<>();*/
}
