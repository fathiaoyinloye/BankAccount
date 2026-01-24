package BankApplication.controllers;

import BankApplication.data.models.Account;
import BankApplication.dtos.requests.DepositRequest;
import BankApplication.dtos.requests.WithdrawRequest;
import BankApplication.exceptions.InsufficientFundException;
import BankApplication.exceptions.InvalidAmountException;
import BankApplication.exceptions.InvalidPasswordException;
import BankApplication.exceptions.NoTransactionMadeException;
import BankApplication.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Account account, DepositRequest request) {
        try {
            return ResponseEntity.status(CREATED).body(accountService.deposit(account, request));
        } catch (InvalidAmountException e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());

        }

    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody Account account, WithdrawRequest request) {
        try {
            return ResponseEntity.status(CREATED).body(accountService.withdraw(account, request));
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());

        } catch (InsufficientFundException e) {
            return ResponseEntity.status(NOT_ACCEPTABLE).body(e.getMessage());

        }
    }

        @GetMapping("/checkBalance")
        public ResponseEntity<?> checkBalance(@RequestBody Account account, String pin) {
            try {
                return ResponseEntity.status(OK).body(accountService.checkBalance(account, pin));
            } catch (InvalidPasswordException e) {
                return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());

            }
        }

            @GetMapping("/checkTransactionsHistory")
            public ResponseEntity<?> viewTransactionHistory(@RequestBody Account account, String pin) {
                try {
                    return ResponseEntity.status(OK).body(accountService.viewTransactionHistory(account));
                } catch (NoTransactionMadeException e) {
                    return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());

                }

    }
}
