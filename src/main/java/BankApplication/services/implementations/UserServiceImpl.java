package BankApplication.services.implementations;

import BankApplication.data.models.Account;
import BankApplication.data.models.User;
import BankApplication.data.repositories.AccountRepository;
import BankApplication.services.interfaces.UserService;
import BankApplication.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AccountRepository accountRepository;


    /*@Override
    public void Register(RegisterRequest request, Account account, User user) {
        Mapper.MapRegisterRequest(request,account, user);
        accountRepository.save(account);
    }*/

    @Override
    public void createAccount() {

    }

    @Override
    public void checkBalance() {

    }

    @Override
    public void deposit() {


    }

    @Override
    public void withdraw() {

    }
}
