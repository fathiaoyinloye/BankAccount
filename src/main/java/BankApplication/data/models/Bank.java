package BankApplication.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
public class Bank {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NonNull
    @NotBlank(message = "Bank Name Cannot Be Blank")
    private String bankName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private List<User> users;

    private int  bankCode;

    private int noOfAccounts;


}
