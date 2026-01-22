package BankApplication.services.interfaces;

import BankApplication.data.models.Account;
import BankApplication.data.models.Transaction;
import BankApplication.dtos.requests.DepositRequest;
import BankApplication.dtos.requests.WithdrawRequest;
import BankApplication.dtos.responses.CheckBalanceResponse;


public interface AccountService {
    Transaction deposit(Account account, DepositRequest depositRequest);
    Transaction withdraw(Account account, WithdrawRequest request);
}
