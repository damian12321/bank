package pl.bank.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "transaction_type")
    private String transactionType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date")
    private Date date;

    public Transaction() {
    }

    public Transaction(String transactionType, Account account, Date date) {
        this.transactionType = transactionType;
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


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionType='" + transactionType + '\'' +
                ", date=" + date +
                '}';
    }
}
