package BankApplication.config;

import BankApplication.data.models.Bank;
import BankApplication.data.models.CBN;
import BankApplication.data.repositories.BankRepository;
import BankApplication.data.repositories.CBNRepository;
import BankApplication.utils.Nuban;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CBNCreation implements CommandLineRunner {
    private final CBNRepository cbnRepository;
    private final BankRepository bankRepository;

    public CBNCreation(CBNRepository cbnRepository, BankRepository bankRepository){
        this.cbnRepository = cbnRepository;
        this.bankRepository = bankRepository;
    }



    @Override
    public void run(String... args) throws Exception {
        if(cbnRepository.count() == 0){
            CBN cbn = new CBN();
            cbn.setName("cbn");
            HashMap<String, String> banksAndCodes = Nuban.registeredBankAndCode();
            for (Map.Entry<String, String> entry : banksAndCodes.entrySet()) {
                Bank bank = new Bank();
                bank.setBankName(entry.getKey());
                bank.setBankCode(entry.getValue());
                bankRepository.save(bank);
            }

        }

    }
}
