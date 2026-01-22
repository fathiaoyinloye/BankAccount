package BankApplication.services.interfaces;

import BankApplication.data.models.Account;
import BankApplication.data.models.Transaction;
import BankApplication.dtos.requests.DepositRequest;
import BankApplication.dtos.requests.WithdrawRequest;
import BankApplication.dtos.responses.CheckBalanceResponse;

import java.util.List;


public interface AccountService {
    Transaction deposit(Account account, DepositRequest depositRequest);
    Transaction withdraw(Account account, WithdrawRequest request);
    CheckBalanceResponse checkBalance(Account account, String pin);
    List<Transaction> viewTransactionHistory(Account account) ;

    }
