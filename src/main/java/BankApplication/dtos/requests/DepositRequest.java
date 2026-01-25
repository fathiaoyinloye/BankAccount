package BankApplication.dtos.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositRequest {
    private String bankName;
    private String accountNumber;

    @NonNull
    private BigDecimal amount;

    private String description;
}
