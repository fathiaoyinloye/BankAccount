package BankApplication.services.implementations;

import BankApplication.data.repositories.AccountRepository;
import BankApplication.data.repositories.BankRepository;
import BankApplication.data.repositories.UserRepository;
import BankApplication.dtos.requests.*;
import BankApplication.dtos.responses.CheckBalanceResponse;
import BankApplication.exceptions.BankDoesNotExistException;
import BankApplication.exceptions.InvalidAccountNumberException;
import BankApplication.exceptions.InvalidAmountException;
import BankApplication.exceptions.UnRegisteredUserException;
import BankApplication.services.interfaces.AccountService;
import BankApplication.services.interfaces.BankService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BankImplTest {
    @Autowired
    private BankService bankService ;

    @Autowired
    private AccountService accountService ;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Test
   @Transactional
    void userRepoEmpty_BankAddedAUser_BankHasOneUser_UserRepoHasOneUser(){
        assertEquals(0,userRepository.count());
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("fatia@gmail.com");
        request.setFirstName("Fathia");
        request.setLastName("Oyinloye");
        request.setUserName("dapo");
        request.setBankName("uba");
        request.setPassword("fathia");
        request.setPin("1234");
        bankService.registerUser(request);
        assertEquals(1, bankRepository.findByBankName("uba").getUsers().size());
        assertEquals(1,userRepository.count());

    }


    @Test
    void userRepoEmpty_BankThatDoesNotExistAddedAUser_ThrExist(){
        assertEquals(0,userRepository.count());
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("fatia@gmail.com");
        request.setFirstName("Fathia");
        request.setLastName("Oyinloye");
        request.setUserName("dapo");
        request.setBankName("hjlklj");
        request.setPassword("fathia");
       assertThrows(BankDoesNotExistException.class, ()-> bankService.registerUser(request));

    }
    @Test
    void userRepoEmpty_BankTryToAddUserWithIncorrectMail_ThrowsException(){
        assertEquals(0,userRepository.count());
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("fatia");
        request.setFirstName("Fathia");
        request.setLastName("Oyinloye");
        request.setUserName("");
        request.setBankName("uba");
        request.setPassword("fathia");
        //assertThrows(ConstraintViolationException.class, ()-> bankService.registerUser(request));

    }

    @Test
    @Transactional
    void testThatPasswordIsHashed(){
        assertEquals(0,userRepository.count());
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("fatia@gmail.com");
        request.setFirstName("Fathia");
        request.setLastName("Oyinloye");
        request.setUserName("dapo");
        request.setBankName("uba");
        request.setPassword("fathia");
        bankService.registerUser(request);
        assertEquals(1, bankRepository.findByBankName("uba").getUsers().size());
        assertEquals(1,userRepository.count());
        assertNotEquals("fathia", bankRepository.findByBankName("uba").getUsers().getFirst().getPassword());


    }
    @Test
    @Transactional
    void testThatUserIsRegistered_UserAutomaticallyHaveAnAccount_AccountPinIsHasshed(){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("fatia@gmail.com");
        request.setFirstName("Tolu");
        request.setLastName("Bola");
        request.setUserName("dapo");
        request.setBankName("afribank");
        request.setPassword("fathia");
        request.setPin("1234");
        bankService.registerUser(request);
        assertEquals(1, bankRepository.findByBankName("afribank").getUsers().size());
        assertEquals(1,userRepository.count());
        assertEquals(1,accountRepository.count());
        assertEquals(   1, bankRepository.findByBankName("afribank").getUsers().getFirst().getAccounts().size());
        assertNotEquals("1234", bankRepository.findByBankName("afribank").getUsers().getFirst().getAccounts().getFirst().getPin());
    }

    @Test
    @Transactional
    void userRepoEmpty_BankA_AddedUserA_BankBAddedUserA_bvnIsSame(){
        assertEquals(0,userRepository.count());
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("fatia@gmail.com");
        request.setFirstName("Fathia");
        request.setLastName("Oyinloye");
        request.setUserName("dapo");
        request.setBankName("uba");
        request.setPassword("fathia");
        request.setPin("1234");
        bankService.registerUser(request);
        assertEquals(1, bankRepository.findByBankName("uba").getUsers().size());
        assertEquals(1,userRepository.count());


        RegisterUserRequest request2 = new RegisterUserRequest();
        request.setEmail("fatia@gmail.com");
        request.setFirstName("Fathia");
        request.setLastName("Oyinloye");
        request.setUserName("dapo");
        request.setBankName("afribank");
        request.setPassword("fathia");
        request.setPin("1234");
        request.setAccountHolder(true);
        request.setBvn("00014503311");
        bankService.registerUser(request);
        assertEquals(2,userRepository.count());
        assertEquals("00014503311", userRepository.findAll().get(1).getBvn());

    }
    @Test
    @Transactional
    void userRepoEmpty_BankA_AddedUserA_BankACreatedAnotherAccountForUserA_BankAHasOneUser_UserAHasTwoAccount(){
        assertEquals(0,userRepository.count());
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("fatia@gmail.com");
        request.setFirstName("Fathia");
        request.setLastName("Oyinloye");
        request.setUserName("dapo");
        request.setBankName("uba");
        request.setPassword("fathia");
        request.setPin("1234");
        bankService.registerUser(request);
        assertEquals(1, bankRepository.findByBankName("uba").getUsers().size());
        assertEquals(1,userRepository.count());


        CreateAccountRequest request2 = new CreateAccountRequest();
        request2.setBvn("00014503311");
        request2.setBankName("uba");
        request2.setPin("3421");
        bankService.createAccount(request2);
        assertEquals(1, bankRepository.findByBankName("uba").getUsers().size());
        assertEquals(1,userRepository.count());
        assertEquals(2, userRepository.findAll().getFirst().getAccounts().size());

    }
    @Test
    void userCreateAccount_NotRegisted_ThrowUnRegisteredException(){
        CreateAccountRequest request = new CreateAccountRequest();
        request.setBvn("00014503311");
        request.setBankName("uba");
        request.setPin("3421");
        assertThrows(UnRegisteredUserException.class, ()-> bankService.createAccount(request));



    }
    @Test
    @Transactional
    void BankRegisterUserA_UseDeposited1k_BalanceIs1k(){
        assertEquals(0,userRepository.count());
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("fatia@gmail.com");
        request.setFirstName("Fathia");
        request.setLastName("Oyinloye");
        request.setUserName("dapo");
        request.setBankName("uba");
        request.setPassword("fathia");
        request.setPin("1234");
        bankService.registerUser(request);
        assertEquals(1, bankRepository.findByBankName("uba").getUsers().size());
        assertEquals(1,userRepository.count());


        CheckBalanceRequest balanceRequest = new CheckBalanceRequest();
        balanceRequest.setBankName("uba");
        balanceRequest.setPin("1234");
        balanceRequest.setAccountNumber("0014503324");
        CheckBalanceResponse response = bankService.checkBalance(balanceRequest);
        assertEquals(BigDecimal.valueOf(0), response.getBalance());

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(BigDecimal.valueOf(1000));
        depositRequest.setBankName("uba");
        depositRequest.setDescription("for food");
        depositRequest.setAccountNumber("0014503324");
        assertEquals(0, bankRepository.findByBankName("uba").getUsers().getFirst().getAccounts().getFirst().getTransactions().size());
        bankService.deposit(depositRequest);
        assertEquals(1, bankRepository.findByBankName("uba").getUsers().getFirst().getAccounts().getFirst().getTransactions().size());
        response = bankService.checkBalance(balanceRequest);
        assertEquals(BigDecimal.valueOf(1000), response.getBalance());

    }

    @Test
    @Transactional
    void BankRegisterUserA_UseDeposited1kWithInvalidAccountNumber_ThrowsException(){
        assertEquals(0,userRepository.count());
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("fatia@gmail.com");
        request.setFirstName("Fathia");
        request.setLastName("Oyinloye");
        request.setUserName("dapo");
        request.setBankName("uba");
        request.setPassword("fathia");
        request.setPin("1234");
        bankService.registerUser(request);
        assertEquals(1, bankRepository.findByBankName("uba").getUsers().size());
        assertEquals(1,userRepository.count());

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(BigDecimal.valueOf(1000));
        depositRequest.setBankName("uba");
        depositRequest.setDescription("for food");
        depositRequest.setAccountNumber("00503324");
        assertEquals(0, bankRepository.findByBankName("uba").getUsers().getFirst().getAccounts().getFirst().getTransactions().size());
        assertThrows(InvalidAccountNumberException.class, ()-> bankService.deposit(depositRequest));
        assertEquals(0, bankRepository.findByBankName("uba").getUsers().getFirst().getAccounts().getFirst().getTransactions().size());

    }
    @Test
    @Transactional
    void BankRegisterUserA_UseDepositedMinus1_ThrowsException() {
        assertEquals(0, userRepository.count());
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("fatia@gmail.com");
        request.setFirstName("Fathia");
        request.setLastName("Oyinloye");
        request.setUserName("dapo");
        request.setBankName("uba");
        request.setPassword("fathia");
        request.setPin("1234");
        bankService.registerUser(request);
        assertEquals(1, bankRepository.findByBankName("uba").getUsers().size());
        assertEquals(1, userRepository.count());


        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(BigDecimal.valueOf(-1000));
        depositRequest.setBankName("uba");
        depositRequest.setDescription("for food");
        depositRequest.setAccountNumber("0014503324");
        assertEquals(0, bankRepository.findByBankName("uba").getUsers().getFirst().getAccounts().getFirst().getTransactions().size());
        assertThrows(InvalidAmountException.class, () -> bankService.deposit(depositRequest));
        assertEquals(0, bankRepository.findByBankName("uba").getUsers().getFirst().getAccounts().getFirst().getTransactions().size());

    }

    @Test
    @Transactional
    void BankRegisterUserA_UseDeposited1k_BalanceIs1k_Withdraw700h_BalanceIs300h(){
        assertEquals(0,userRepository.count());
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("fatia@gmail.com");
        request.setFirstName("Fathia");
        request.setLastName("Oyinloye");
        request.setUserName("dapo");
        request.setBankName("uba");
        request.setPassword("fathia");
        request.setPin("1234");
        bankService.registerUser(request);
        assertEquals(1, bankRepository.findByBankName("uba").getUsers().size());
        assertEquals(1,userRepository.count());


        CheckBalanceRequest balanceRequest = new CheckBalanceRequest();
        balanceRequest.setBankName("uba");
        balanceRequest.setPin("1234");
        balanceRequest.setAccountNumber("0014503324");
        CheckBalanceResponse response = bankService.checkBalance(balanceRequest);
        assertEquals(BigDecimal.valueOf(0), response.getBalance());

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(BigDecimal.valueOf(1000));
        depositRequest.setBankName("uba");
        depositRequest.setDescription("for food");
        depositRequest.setAccountNumber("0014503324");
        assertEquals(0, bankRepository.findByBankName("uba").getUsers().getFirst().getAccounts().getFirst().getTransactions().size());
        bankService.deposit(depositRequest);
        assertEquals(1, bankRepository.findByBankName("uba").getUsers().getFirst().getAccounts().getFirst().getTransactions().size());
        response = bankService.checkBalance(balanceRequest);
        assertEquals(BigDecimal.valueOf(1000), response.getBalance());

        WithdrawRequest withdrawRequest = new WithdrawRequest();


    }

    }