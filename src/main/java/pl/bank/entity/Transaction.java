package pl.bank.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "transaction_type")
    private String transactionType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date")
    private Date date;

    public Transaction() {
    }

    public Transaction(String transactionType, Account account, Date date) {
        this.transactionType = transactionType;
        this.account = account;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
