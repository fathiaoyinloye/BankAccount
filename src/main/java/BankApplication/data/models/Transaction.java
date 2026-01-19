package BankApplication.data.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
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
    private BigDecimal amount;
    private TransactionStatus status;

}
