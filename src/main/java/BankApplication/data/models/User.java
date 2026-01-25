package BankApplication.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "account_users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String firstName;
    private String lastName;


    private String email;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private List<Account> accounts = new ArrayList<>();
   // private String kycDocument;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private UUID bankID;
    private String bvn;
    public void addToAccounts(Account account){
        accounts.add(account);
    }




}
