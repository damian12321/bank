package pl.springbank.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pl.springbank.bank.validation.ValidEmail;

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

    @Size(min = 2, message = "Name should have at least 2 characters")
    private String firstName;

    @Size(min = 2, message = "Last name should have at least 2 characters")
    private String lastName;

    @Size(min = 4, message = "Password should have at least 4 characters")
    private String password;

    @Column(unique = true)
    private int accountNumber;

    @ValidEmail
    @Column(unique = true)
    private String email;

    @Min(value = 1000, message = "Pin number should have at least 4 characters")
    private int pinNumber;

    private float balance;

    private int loginAttempts;

    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "account")
    private List<Transaction> transactionList;

    public Account(int id, String firstName, String lastName, String password, int accountNumber, int pinNumber, float balance, int loginAttempts, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.accountNumber = accountNumber;
        this.pinNumber = pinNumber;
        this.balance = balance;
        this.loginAttempts = loginAttempts;
        this.isActive = isActive;
    }

    public Account() {

    }
    
    @JsonIgnore
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    @JsonProperty
    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonIgnore
    public int getPinNumber() {
        return pinNumber;
    }
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
    @JsonProperty
    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }
}
