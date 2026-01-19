package BankApplication.dtos.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class DepositRequest {
    private BigDecimal amount;
    private String description;
}
