package BankApplication.data.models;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String description;
    private Instant time = Instant.now();
    private UUID accountId;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

}
