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
public class Account{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String accountNumber;
    private String pin;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private List<Transaction> transactions = new ArrayList<>();

    public void addToTransaction(Transaction transaction){
        transactions.add(transaction);
    }
}
