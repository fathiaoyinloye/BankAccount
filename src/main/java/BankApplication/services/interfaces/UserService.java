package BankApplication.services.interfaces;

import BankApplication.data.models.Account;
import BankApplication.data.models.User;
import BankApplication.dtos.requests.RegisterUserRequest;

public interface UserService {
    //void Register(RegisterUserRequest request);
    void createAccount();
    void checkBalance();
    void deposit();
    void withdraw();

}
