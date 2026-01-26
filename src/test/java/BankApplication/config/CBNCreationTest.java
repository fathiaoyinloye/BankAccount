package BankApplication.config;

import BankApplication.data.repositories.BankRepository;
import BankApplication.data.repositories.CBNRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class CBNCreationTest {

    @Autowired
    private CBNRepository cbnRepository;
    @Autowired
    private BankRepository bankRepository;

    @Test
    void testThatCBNIsCreatedByDefault(){
        assertFalse(cbnRepository.findAll().isEmpty());
        assertEquals(1, cbnRepository.count());
        assertEquals("cbn", cbnRepository.findAll().getFirst().getName());

    }
    @Test
    void testThatCBNIsCreatedByDefaultAndRegisteredBanksAreAlsoCreated(){
        assertFalse(cbnRepository.findAll().isEmpty());
        assertEquals(1, cbnRepository.count());
        assertFalse(bankRepository.findAll().isEmpty());
        assertEquals(24, bankRepository.count());
    }
}