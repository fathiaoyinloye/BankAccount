package BankApplication.dtos.requests;

import BankApplication.utils.PasswordUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckBalanceRequest {
    private String pin;
    private String bankName;
    private String accountNumber;
}
