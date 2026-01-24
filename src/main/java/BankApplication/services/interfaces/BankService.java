package BankApplication.services.interfaces;


import BankApplication.data.models.Bank;
import BankApplication.dtos.requests.CreateAccountRequest;
import BankApplication.dtos.requests.RegisterCustomerRequest;


public interface BankService {
    void registerCustomer(RegisterCustomerRequest request);
    void createAccount(CreateAccountRequest request, Bank bank);
    void withdraw();
    void transfer();


}
