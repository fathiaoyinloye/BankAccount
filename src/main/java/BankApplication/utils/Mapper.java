package BankApplication.utils;

import BankApplication.data.models.Account;
import BankApplication.data.models.Transaction;
import BankApplication.data.models.TransactionType;
import BankApplication.dtos.requests.DepositRequest;
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
}
