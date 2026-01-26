package BankApplication.dtos.requests;

import BankApplication.utils.PasswordUtil;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateAccountRequest {

    @Size(min= 4, max=  4, message = "Pin must be 4 characters")
    @Digits(integer= 4, fraction = 0)
    private String pin;
    private String bvn;
    private String bankName;

    public void setPin(String pin){this.pin = PasswordUtil.hashPassword(pin);}


}
