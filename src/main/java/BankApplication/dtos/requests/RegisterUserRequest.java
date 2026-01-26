package BankApplication.dtos.requests;

import BankApplication.utils.PasswordUtil;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {
    @NotBlank(message = "FirstName cannot be empty")
    private String firstName;

    @NotBlank(message = "LastName cannot be empty")
    private String lastName;

    @Email(message = "it must be a valid email ")
    private String email;

    @NotBlank(message = "Username cannot be empty")
    private String userName;

    @NotBlank(message = "password cannot be empty")
    private String password;

    @NotBlank(message = "Bank Name cannot be blank")
    private String bankName;

    @Size(min= 4, max=  4, message = "Pin must be 4 characters")
    @Digits(integer= 4, fraction = 0, message = "Pin must be digits")
    private String pin;

    @NotNull
    private boolean accountHolder;

    private String bvn;
    public void setPassword(String password) {
        this.password = PasswordUtil.hashPassword(password);
    }
    public void setPin(String pin) {
        this.pin = PasswordUtil.hashPassword(pin);
    }


}
