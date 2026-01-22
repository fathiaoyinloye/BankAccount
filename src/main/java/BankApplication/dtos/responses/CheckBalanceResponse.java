package BankApplication.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CheckBalanceResponse {
    private BigDecimal balance;
}
