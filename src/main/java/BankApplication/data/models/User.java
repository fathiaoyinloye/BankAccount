package BankApplication.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

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
    private String id;

    private String firstName;
    private String lastName;

    @NotNull
    @Email
    @NotBlank(message = "Email cannot be blank")
    @Column(unique = true)
    private String email;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private List<Account> accounts;
   // private String kycDocument;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
