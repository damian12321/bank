package pl.bank.entity;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="account_id")
    private List<Transaction> transactionList;

    public Account() {
    }

    public Account(int accountNumber, int pinNumber, float balance, Customer customer) {
        this.accountNumber = accountNumber;
        this.pinNumber = pinNumber;
        this.balance = balance;
        this.customer = customer;
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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", pinNumber=" + "*******" +
                ", balance=" + balance +
                ", customer=" + customer +
                ", transactionList=" + transactionList +
                '}';
    }
}
