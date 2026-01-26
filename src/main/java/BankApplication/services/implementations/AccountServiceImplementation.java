package BankApplication.services.implementations;

import BankApplication.data.models.Account;
import BankApplication.data.models.Transaction;
import BankApplication.data.repositories.TransactionRepository;
import BankApplication.dtos.requests.WithdrawRequest;
import BankApplication.dtos.responses.CheckBalanceResponse;
import BankApplication.exceptions.InsufficientFundException;
import BankApplication.exceptions.InvalidAmountException;
import BankApplication.exceptions.InvalidPasswordException;
import BankApplication.exceptions.NoTransactionMadeException;
import BankApplication.services.interfaces.AccountService;
import BankApplication.dtos.requests.DepositRequest;
import BankApplication.utils.Mapper;
import BankApplication.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static BankApplication.data.models.TransactionType.CREDIT;

@Service
public class AccountServiceImplementation implements AccountService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction deposit(Account account, DepositRequest depositRequest) {
        validateAmount(depositRequest);
        Transaction transaction = Mapper.TransactionMapper(depositRequest);
        transaction.setAccountId(account.getId());
        account.addToTransaction(transaction);
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Transaction withdraw(Account account, WithdrawRequest request) {
        validatePin(account, request.getPassword());
        if(checkBalance(account, request.getPassword()).getBalance().compareTo(request.getAmount()) < 0) throw new InsufficientFundException();
        Transaction transaction = Mapper.TransactionMapper(request);
        transaction.setAccountId(account.getId());
        account.addToTransaction(transaction);
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> viewTransactionHistory(Account account) {
        if(account.getTransactions().isEmpty()) throw new NoTransactionMadeException();
        return account.getTransactions();
    }

    @Override
    public CheckBalanceResponse checkBalance(Account account, String pin) {
        validatePin(account, pin);
        CheckBalanceResponse response = new CheckBalanceResponse();
        List<Transaction> transactions = account.getTransactions();
        BigDecimal balance = BigDecimal.valueOf(0);

        if (transactions.isEmpty()) {response.setBalance(balance);return response;}
        for(Transaction transaction : transactions){
            if (transaction.getTransactionType() == CREDIT) balance = balance.add(transaction.getAmount());
            else
                balance =  balance.subtract(transaction.getAmount());
        }
        response.setBalance(balance);
        return response;
    }
    private void validatePin(Account account, String pin){
        if (!PasswordUtil.verifyPassword(pin, account.getPin())) throw new InvalidPasswordException();


    }
    private void validateAmount(DepositRequest request){
        if (request.getAmount().compareTo(BigDecimal.valueOf(50)) < 0) throw new InvalidAmountException();
    }

    private void validateAmount(WithdrawRequest request){
        if (request.getAmount().compareTo(BigDecimal.valueOf(50)) < 0) throw new InvalidAmountException();
    }
}
