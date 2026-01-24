package BankApplication.data.services.implementations;

import BankApplication.data.models.Account;
import BankApplication.data.models.Transaction;
import BankApplication.data.repositories.TransactionRepository;
import BankApplication.dtos.requests.WithdrawRequest;
import BankApplication.exceptions.InsufficientFundException;
import BankApplication.exceptions.InvalidPasswordException;
import BankApplication.services.interfaces.AccountService;
import BankApplication.dtos.requests.DepositRequest;
import BankApplication.exceptions.InvalidAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static BankApplication.data.models.TransactionType.CREDIT;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceImplementationTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionRepository transactionRepository;
    Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setAccountNumber("12356");
        account.setPin("1234");
        transactionRepository.deleteAll();
    }

    @Test
    void testThatAccountCanDeposit_AccountHasOneTransaction(){
        DepositRequest request = new DepositRequest();
        BigDecimal amount = BigDecimal.valueOf(1000);
        request.setAmount(amount);
        Transaction transaction = accountService.deposit(account, request);
        assertNotNull(transaction);
        assertEquals(CREDIT, transaction.getTransactionType());
        assertEquals(amount, transaction.getAmount());
        assertEquals(1, transactionRepository.count());


    }
    @Test
    void Deposited1000_AccountHasOneTransaction_BalanceIs1000(){
        assertEquals(BigDecimal.valueOf(0), accountService.checkBalance(account, "1234").getBalance());
        DepositRequest request = new DepositRequest();
        BigDecimal amount = BigDecimal.valueOf(1000);
        request.setAmount(amount);
        Transaction transaction = accountService.deposit(account, request);
        assertNotNull(transaction);
        assertEquals(CREDIT, transaction.getTransactionType());
        assertEquals(amount, transaction.getAmount());
        assertEquals(1, account.getTransactions().size());
        assertEquals(BigDecimal.valueOf(1000),accountService.checkBalance(account, "1234").getBalance());

    }
    @Test
    void Deposited1000_AccountHasOneTransaction_BalanceIs1000_Deposited1000_BalanceIs2000(){
        assertEquals(BigDecimal.valueOf(0), accountService.checkBalance(account, "1234").getBalance());
        DepositRequest request = new DepositRequest();
        BigDecimal amount = BigDecimal.valueOf(1000);
        request.setAmount(amount);
        Transaction transaction = accountService.deposit(account, request);
        assertNotNull(transaction);
        assertEquals(CREDIT, transaction.getTransactionType());
        assertEquals(amount, transaction.getAmount());
        assertEquals(1, account.getTransactions().size());
        assertEquals(BigDecimal.valueOf(1000),accountService.checkBalance(account, "1234").getBalance());
        assertEquals(amount, transaction.getAmount());
        accountService.deposit(account, request);
        assertEquals(2, account.getTransactions().size());
        assertEquals(BigDecimal.valueOf(2000),accountService.checkBalance(account, "1234").getBalance());

    }

    @Test
    void testThaAmountLessThanFiftyZeroCannotBeDeposited(){
        DepositRequest request = new DepositRequest();
        BigDecimal amount = BigDecimal.valueOf(43);
        request.setAmount(amount);
        assertThrows(InvalidAmountException.class, () -> accountService.deposit(account, request));

    }
    @Test
    void testThatWecanAddDescriptionToOurDeposit(){
        DepositRequest request = new DepositRequest();
        BigDecimal amount = BigDecimal.valueOf(1000);
        request.setAmount(amount);
        request.setDescription("Money For Food");
        Transaction transaction = accountService.deposit(account, request);
        assertNotNull(transaction);
        assertEquals(CREDIT, transaction.getTransactionType());
        assertEquals(amount, transaction.getAmount());
        assertEquals("Money For Food", transaction.getDescription());
    }
    @Test
    void Deposited2000_Withdraw1000_Balance1000(){
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(BigDecimal.valueOf(2000));
        accountService.deposit(account, depositRequest);
        assertEquals(BigDecimal.valueOf(2000), accountService.checkBalance(account, "1234").getBalance());
        WithdrawRequest request = new WithdrawRequest();
        BigDecimal amount = BigDecimal.valueOf(1000);
        request.setAmount(amount);
        request.setPassword("1234");
        request.setDescription("Money For Food");
        accountService.withdraw(account, request);
        assertEquals(BigDecimal.valueOf(1000), accountService.checkBalance(account, "1234").getBalance());


    }
    @Test
    void BalanceIs0_Withdraw2000_throwsException(){
        assertEquals(BigDecimal.valueOf(0), accountService.checkBalance(account, "1234").getBalance());
        WithdrawRequest request = new WithdrawRequest();
        BigDecimal amount = BigDecimal.valueOf(1000);
        request.setAmount(amount);
        request.setPassword("1234");
        request.setDescription("Money For Food");
        assertThrows(InsufficientFundException.class,()-> accountService.withdraw(account, request));
        assertEquals(BigDecimal.valueOf(0), accountService.checkBalance(account, "1234").getBalance());

    }

    @Test
    void Deposit1000_Withdraw1000WithInvalidPassword_throwsInvalidPaswordException_BalanceIs1000(){
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(BigDecimal.valueOf(1000));
        accountService.deposit(account, depositRequest);
        assertEquals(BigDecimal.valueOf(1000), accountService.checkBalance(account, "1234").getBalance());
        assertEquals(BigDecimal.valueOf(1000), accountService.checkBalance(account, "1234").getBalance());
        WithdrawRequest request = new WithdrawRequest();
        BigDecimal amount = BigDecimal.valueOf(1000);
        request.setAmount(amount);
        request.setPassword("1834");
        assertThrows(InvalidPasswordException.class,()-> accountService.withdraw(account, request));
        assertEquals(BigDecimal.valueOf(1000), accountService.checkBalance(account, "1234").getBalance());

    }


}