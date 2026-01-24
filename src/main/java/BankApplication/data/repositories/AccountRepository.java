package BankApplication.data.repositories;

import BankApplication.data.models.Account;
import BankApplication.data.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, String> {
   // List<Account> findAccountByUserId(UUID userId);

}
