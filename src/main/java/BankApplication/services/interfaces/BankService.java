package BankApplication.services.interfaces;


import BankApplication.data.models.Bank;
import BankApplication.dtos.requests.CreateAccountRequest;
import BankApplication.dtos.requests.RegisterRequest;
import BankApplication.dtos.requests.RegisterUserRequest;
import org.springframework.stereotype.Service;




public interface BankService {
    public void registerUser(RegisterUserRequest request);
        void registerCustomer(RegisterRequest request, Bank bank);
    void createAccount(CreateAccountRequest request, Bank bank);
    void withdraw();
    void transfer();


}
