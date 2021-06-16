package pl.springbank.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min=2, message="Name should have at least 2 characters")
    private String firstName;

    @Size(min=2, message="Last name should have at least 2 characters")
    private String lastName;

    @Size(min=4, message="Password should have at least 4 characters")
    private String password;

    @Column(unique = true)
    private int accountNumber;

    @Min(value = 1000, message = "Pin number should have at least 4 characters")
    private int pinNumber;

    private float balance;

    private int loginAttempts;

    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "account")
    private List<Transaction> transactionList;

    public Account() {
    }

    public Account(int id, int accountNumber, int pinNumber, float balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.loginAttempts = 3;
        this.isActive = true;
        this.pinNumber = pinNumber;
    }

    public Account(int accountNumber, int pinNumber, float balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.loginAttempts = 3;
        this.isActive = true;
        this.pinNumber = pinNumber;
    }

    @JsonIgnore
    public int getPinNumber() {
        return pinNumber;
    }

    @JsonProperty
    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public boolean getIsActive()
    {
    return isActive;
    }
    public void setIsActive(boolean isActive)
    {
        this.isActive=isActive;
    }

}
