package pl.bank.dao;

import pl.bank.entity.Transaction;

import java.util.List;

public interface TransactionsDao {
    public List<Transaction> getTransactions();
    public String createTransaction(Transaction transaction);
    public String deleteTransaction(int number);
    public Transaction getTransaction(int id);
    public Transaction updateTransaction(Transaction transaction);
}
