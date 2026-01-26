package BankApplication.dtos.responses;

import BankApplication.data.models.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class DepositResponse {
    private BigDecimal deposit;
    private String description;
    private Instant time = Instant.now();
    private UUID accountId;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private BigDecimal amount;


}
