package BankApplication.utils;

import BankApplication.data.models.*;
import BankApplication.dtos.requests.*;

import static BankApplication.data.models.Role.CLIENT;

public class Mapper {
    public static void RegisterUserMap(RegisterUserRequest request, User user,Bank bank, Account account){
        user.setRole(CLIENT);
        user.setUsername(request.getUserName());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setBankID(bank.getId());
        account.setUserId(user.getId());
        account.setAccountNumber(Mapper.generateAccountNumber(bank));
        account.setBankUUID(bank.getId());
        account.setPin(request.getPin());

    }

    public static  String generateAccountNumber(Bank bank){
        String serialNumber =  generateSerialNumber(bank);
        int nubanCheck = Nuban.calNubanLastDigitCode( serialNumber , bank.getBankName());
        return serialNumber + nubanCheck;
    }

    public static  String generateBVN(Bank bank){
        String serialNumber =  generateSerialNumber(bank);
                if(bank.getNoOfAccounts() < 10) return 0 + serialNumber + bank.getNoOfAccounts();
        return serialNumber + bank.getNoOfAccounts();
    }

    public static String generateSerialNumber(Bank bank) {
        bank.setNoOfAccounts(bank.getNoOfAccounts() + 1);
        if (bank.getNoOfAccounts() < 10) return "00145"  + bank.getBankCode() + (bank.getNoOfAccounts());
        return "0234" + bank.getBankCode() + (bank.getNoOfAccounts());
    }







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





}
