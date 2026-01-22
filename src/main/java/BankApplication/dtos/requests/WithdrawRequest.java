package BankApplication.dtos.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class WithdrawRequest {
    private BigDecimal amount;
    private String description;
    private String password;
}
