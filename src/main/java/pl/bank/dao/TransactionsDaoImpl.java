package pl.bank.dao;

import org.springframework.stereotype.Repository;
import pl.bank.entity.Transaction;

import java.util.List;

@Repository
public class TransactionsDaoImpl implements TransactionsDao {
    @Override
    public List<Transaction> getTransactions() {
        return null;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public String deleteTransaction(int id) {
        return null;
    }

    @Override
    public Transaction getTransaction(int id) {
        return null;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return null;
    }
}