package pl.springbank.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int accountNumber;

    private int pinNumber;

    private float balance;

    private int loginAttempts;

    private boolean isActive;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
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
    @JsonIgnore
    public int getPinNumber() {
        return pinNumber;
    }
    @JsonProperty
    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNumber == account.accountNumber && Float.compare(account.balance, balance) == 0 && loginAttempts == account.loginAttempts && isActive == account.isActive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance, loginAttempts, isActive);
    }

    @Override
    public String toString() {
        return "Account{" +
                "AccountNumber=" + accountNumber +
                ", balance=" + balance +
                ", loginAttempts=" + loginAttempts +
                ", isActive=" + isActive +
                ", transactionList=" + transactionList +
                '}';
    }
}
