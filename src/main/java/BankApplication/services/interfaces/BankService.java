package BankApplication.services.interfaces;


import BankApplication.data.models.Bank;
import BankApplication.dtos.requests.CreateAccountRequest;
import BankApplication.dtos.requests.DepositRequest;
import BankApplication.dtos.requests.RegisterUserRequest;


public interface BankService {
        void createAccount(CreateAccountRequest request);
        void withdraw();
        void transfer();
        void registerUser(RegisterUserRequest request);
        void deposit(DepositRequest request);



}
