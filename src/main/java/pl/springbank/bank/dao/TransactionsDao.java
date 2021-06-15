package pl.springbank.bank.dao;

import pl.springbank.bank.entity.Transaction;

import java.util.List;

public interface TransactionsDao {
    public List<Transaction> getTransactions();

    public Transaction createTransaction(Transaction transaction);

    public String deleteTransaction(int id);

    public Transaction getTransaction(int id);

    public Transaction updateTransaction(Transaction transaction);

}
