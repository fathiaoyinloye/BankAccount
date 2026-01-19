package BankApplication.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Account{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String accountNumber;
    private String password;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private List<Transaction> transactions;

}
