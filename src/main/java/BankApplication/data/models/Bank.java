package BankApplication.data.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bank {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;
    private String bankName;
    @OneToMany
    List<Account> accounts;

}
