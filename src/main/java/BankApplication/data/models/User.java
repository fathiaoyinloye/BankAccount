package BankApplication.data.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "account_users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String firstName;
    private String lastName;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private List<Account> accounts;
    private String kycDocument;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
