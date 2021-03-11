package pl.bank.service;

import pl.bank.entity.Transaction;

import java.util.List;

public interface TransactionsService {
    public List<Transaction> getTransactions();

    public String createTransaction(Transaction transaction);

    public String deleteTransaction(int number);

    public Transaction getTransaction(int id);

    public Transaction updateTransaction(Transaction transaction);
}
