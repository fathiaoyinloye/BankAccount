package BankApplication.utils;

import BankApplication.data.models.Account;
import BankApplication.data.models.Bank;
import BankApplication.data.models.Transaction;
import BankApplication.data.models.TransactionType;
import BankApplication.dtos.requests.DepositRequest;
import BankApplication.dtos.requests.RegisterCustomerRequest;
import BankApplication.dtos.requests.WithdrawRequest;

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
        if (bank.getNoOfAccounts() < 10) return "00012345" + (bank.getNoOfAccounts() - 1);
        return "0001234" + (bank.getNoOfAccounts() - 1);
    }
}
