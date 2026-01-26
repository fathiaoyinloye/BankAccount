package BankApplication.data.repositories;

import BankApplication.data.models.CBN;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CBNRepository extends JpaRepository <CBN, UUID>{
    CBN findByName(String name);

}


