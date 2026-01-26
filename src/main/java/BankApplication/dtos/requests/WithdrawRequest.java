package BankApplication.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class WithdrawRequest {
    private BigDecimal amount;
    private String description;
    private String password;
    private String bankName;
    private String accountNumber;
}
