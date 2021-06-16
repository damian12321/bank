package pl.springbank.bank.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.springbank.bank.enums.TransactionType;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Account account;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private float amount;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date;

    private String description;

    public Transaction() {
    }

    public Transaction(TransactionType transactionType, float amount, Date date, String description) {
        this.transactionType = transactionType;
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public Transaction(int id, TransactionType transactionType, float amount, Date date, String description) {
        this.id = id;
        this.transactionType = transactionType;
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && Float.compare(that.amount, amount) == 0 && transactionType == that.transactionType && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionType, amount, description);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
