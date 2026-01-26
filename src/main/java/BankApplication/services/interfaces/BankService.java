package BankApplication.services.interfaces;


import BankApplication.data.models.Bank;
import BankApplication.dtos.requests.*;
import BankApplication.dtos.responses.CheckBalanceResponse;
import BankApplication.dtos.responses.DepositResponse;
import BankApplication.dtos.responses.WithdrawResponse;


public interface BankService {
        void createAccount(CreateAccountRequest request);
        void registerUser(RegisterUserRequest request);
        DepositResponse deposit(DepositRequest request);
        CheckBalanceResponse checkBalance(CheckBalanceRequest request);
        WithdrawResponse withdraw(WithdrawRequest request);

        void transfer();




}
