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
    public String createTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public String deleteTransaction(int number) {
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
