package BankApplication.data.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Bank {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String bankName;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private List<User> users;

}
