package BankApplication.services.implementations;

import BankApplication.data.models.Account;
import BankApplication.data.models.Transaction;
import BankApplication.data.repositories.TransactionRepository;
import BankApplication.dtos.requests.WithdrawRequest;
import BankApplication.dtos.responses.CheckBalanceResponse;
import BankApplication.dtos.responses.WithdrawResponse;
import BankApplication.exceptions.InvalidAmountException;
import BankApplication.exceptions.InvalidPasswordException;
import BankApplication.services.interfaces.AccountService;
import BankApplication.dtos.requests.DepositRequest;
import BankApplication.utils.Mapper;
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
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Transaction withdraw(Account account, WithdrawRequest request) {
        if(!request.getPassword().equals(account.getPassword())) throw new InvalidPasswordException();
        Transaction transaction = Mapper.TransactionMapper(request);
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public CheckBalanceResponse checkBalance() {
        CheckBalanceResponse response = new CheckBalanceResponse();
            List<Transaction> transactions = transactionRepository.findAll();
            BigDecimal balance = BigDecimal.valueOf(0);
            System.out.println("before " + balance);
        for(Transaction transaction : transactions){
            if (transaction.getTransactionType() == CREDIT) balance = balance.add(transaction.getAmount());
            balance =  balance.subtract(transaction.getAmount());

        }
        response.setBalance(balance);
        System.out.println(balance);

        return response;
    }

    private void validateAmount(DepositRequest request){
        if (request.getAmount().compareTo(BigDecimal.valueOf(50)) < 0) throw new InvalidAmountException();
    }

    private void validateAmount(WithdrawRequest request){
        if (request.getAmount().compareTo(BigDecimal.valueOf(50)) < 0) throw new InvalidAmountException();
    }
}
