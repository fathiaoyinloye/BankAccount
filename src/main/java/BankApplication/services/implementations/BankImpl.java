package BankApplication.services.implementations;

import BankApplication.data.models.Account;
import BankApplication.data.models.Bank;
import BankApplication.data.models.User;
import BankApplication.data.repositories.AccountRepository;
import BankApplication.data.repositories.BankRepository;
import BankApplication.data.repositories.UserRepository;
import BankApplication.dtos.requests.*;
import BankApplication.dtos.responses.CheckBalanceResponse;
import BankApplication.dtos.responses.DepositResponse;
import BankApplication.dtos.responses.WithdrawResponse;
import BankApplication.exceptions.BankDoesNotExistException;
import BankApplication.exceptions.InvalidAccountNumberException;
import BankApplication.exceptions.InvalidBvnException;
import BankApplication.exceptions.UnRegisteredUserException;
import BankApplication.services.interfaces.AccountService;
import BankApplication.services.interfaces.BankService;
import BankApplication.services.interfaces.UserService;
import BankApplication.utils.Mapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

import static BankApplication.utils.Mapper.generateBVN;

@Service
public class BankImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private  AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    AccountService accountService;

    @Override
    @Transactional
    @Validated
    public void registerUser(@Valid RegisterUserRequest request){
        validateBankExisted(request.getBankName());
        if(request.isAccountHolder()) registerAccountHolder(request);
        else registerNonAccountHolder(request);
}

    private void registerNonAccountHolder(RegisterUserRequest request){
        Bank bank = findBank(request.getBankName());
        User user = new User();
        Account account = new Account();
        user.setBvn(generateBVN(bank));
        Mapper.RegisterUserMap(request,user,bank,account);
        accountRepository.save(account);
        user.addToAccounts(account);
        bank.addToUsers(user);
        userRepository.save(user);
        bank.setNoOfAccounts(bank.getNoOfAccounts() + 1);
        bankRepository.save(bank);
    }

    private void registerAccountHolder(RegisterUserRequest request){
        validateBvnExisted(request.getBvn());
        Bank bank = findBank(request.getBankName());
        User user = new User();
        Account account = new Account();
        user.setBvn(request.getBvn());
        Mapper.RegisterUserMap(request,user,bank,account);
        accountRepository.save(account);
        user.addToAccounts(account);
        bank.addToUsers(user);
        userRepository.save(user);
        bank.setNoOfAccounts(bank.getNoOfAccounts() + 1);
        bankRepository.save(bank);
    }

    @Override
    @Transactional
    public void createAccount(CreateAccountRequest request ) {
    Bank bank = findBank(request.getBankName());
    validateBankUser(request);
    User user = findBankUser(request);
    Account account = new Account();
    account.setBankUUID(bank.getId());
    account.setAccountNumber(Mapper.generateAccountNumber(bank));
    account.setPin(request.getPin());
    account.setUserId(user.getId());
    accountRepository.save(account);
    user.addToAccounts(account);
    bank.setNoOfAccounts(bank.getNoOfAccounts() + 1);
    /*userRepository.save(user);
    bankRepository.save(bank);*/
    }

    @Transactional
    @Override
    public DepositResponse deposit(DepositRequest request) {
        validateBankExisted(request.getBankName());
        Bank bank = findBank(request.getBankName());
        validateAccountNumber(request.getAccountNumber(), bank.getId());
        Account account = findAccount(request.getAccountNumber());
        accountService.deposit(account, request);
        return Mapper.DepositResponseMapper(request);


    }
    @Override
    public CheckBalanceResponse checkBalance(CheckBalanceRequest request){
        validateBankExisted(request.getBankName());
        Bank bank = findBank(request.getBankName());
        validateAccountNumber(request.getAccountNumber(), bank.getId());
        Account account = findAccount(request.getAccountNumber());
        return accountService.checkBalance(account,request.getPin());


    }

    @Override
    @Transactional
    public WithdrawResponse withdraw(WithdrawRequest request) {
        validateBankExisted(request.getBankName());
        Bank bank = findBank(request.getBankName());
        validateAccountNumber(request.getAccountNumber(), bank.getId());
        Account account = findAccount(request.getAccountNumber());
        accountService.withdraw(account, request);
        return Mapper.withdrawalResponseMapper(request);


    }


    private Account findAccount(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);

    }
    private  void validateAccountNumber(String accountNumber, UUID bankUUId){
        Account account = findAccount(accountNumber);
        if(account == null) throw  new InvalidAccountNumberException();
        else if(!account.getBankUUID().equals(bankUUId)) throw  new InvalidAccountNumberException();
    }

    @Override
    public void transfer() {

    }

    private void validateBankExisted(String bankName){
        Bank bank = bankRepository.findByBankName(bankName);
        if(bank == null) throw new BankDoesNotExistException();

    }

    private void validateBvnExisted(String userBVN){
        User user = userRepository.findByBvn(userBVN);
        if(user == null) throw new InvalidBvnException();

    }
    private Bank findBank(String bankName){
        return  bankRepository.findByBankName(bankName);
    }

    private User findBankUser(CreateAccountRequest request){
        Bank bank = findBank(request.getBankName());
        List<User> bankUsers = bank.getUsers();
        for(User user : bankUsers){
            if(user.getBvn().equals(request.getBvn())) return user;
        }
        return null;
    }

    private void validateBankUser(CreateAccountRequest request){
        if(findBankUser(request) == null) throw new UnRegisteredUserException();
    }


}
