package BankApplication.utils;

import BankApplication.data.models.*;
import BankApplication.dtos.requests.DepositRequest;
import BankApplication.dtos.requests.RegisterCustomerRequest;
import BankApplication.dtos.requests.RegisterRequest;
import BankApplication.dtos.requests.WithdrawRequest;

import static BankApplication.data.models.Role.CLIENT;

public class Mapper {
    public static Transaction TransactionMapper(DepositRequest request){
        Transaction transaction = new Transaction();
        if(request.getDescription() != null) transaction.setDescription(request.getDescription());
        transaction.setTransactionType(TransactionType.CREDIT);
        transaction.setAmount(request.getAmount());
        return transaction;
    }
    public static Transaction TransactionMapper( WithdrawRequest request){
        Transaction transaction = new Transaction();
        if(request.getDescription() != null) transaction.setDescription(request.getDescription());
        transaction.setTransactionType(TransactionType.DEBIT);
        transaction.setAmount(request.getAmount());
        return transaction;
    }
    public static Account mapAccount(RegisterCustomerRequest request){
        Account account = new Account();
        account.setPin(request.getPin());
        return null;
    }

    public static  String generateAccountNumber(Bank bank){
            String serialNumber =  generateSerialNumber(bank);
            int nubanCheck = Nuban.calNubanLastDigitCode( serialNumber , bank.getBankName());
            return serialNumber + nubanCheck;
          }



    public static String generateSerialNumber(Bank bank) {
        bank.setNoOfAccounts(bank.getNoOfAccounts() + 1);
        if (bank.getNoOfAccounts() < 10) return "00012345" + (bank.getNoOfAccounts());
        return "0001234" + (bank.getNoOfAccounts());
    }

    public static void MapRegisterRequest(RegisterRequest request, Account account, User user){
        user.setEmail(request.getEmail());
        user.setRole(CLIENT);
        user.setPassword(request.getPassword());
        user.setUsername(request.getUserName());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        account.setPin(request.getPin());

    }

}
