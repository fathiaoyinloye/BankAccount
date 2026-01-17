package BankApplication.data.services.interfaces;

import BankApplication.dtos.requests.DepositRequest;
import BankApplication.dtos.responses.DepositResponse;

public interface AccountService {
    DepositResponse deposit(DepositRequest depositRequest);

}
