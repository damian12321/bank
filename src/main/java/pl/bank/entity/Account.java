package pl.bank.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "account_number")
    private int accountNumber;

    @Column(name = "pin_number")
    private int pinNumber;

    @Column(name = "balance")
    private float balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "login_attempts")
    private int loginAttempts;

    @Column(name = "active")
    private boolean isActive;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<Transaction> transactionList;

    public Account() {
    }

    public Account(int accountNumber, float balance, Customer customer, int loginAttempts, boolean isActive) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customer = customer;
        this.loginAttempts = loginAttempts;
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

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
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
        return accountNumber == account.accountNumber && Float.compare(account.balance, balance) == 0 && loginAttempts == account.loginAttempts && isActive == account.isActive && customer.equals(account.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance, customer, loginAttempts, isActive);
    }

    @Override
    public String toString() {
        return "Account{" +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", customer=" + customer +
                ", loginAttempts=" + loginAttempts +
                ", isActive=" + isActive +
                ", transactionList=" + transactionList +
                '}';
    }
}
