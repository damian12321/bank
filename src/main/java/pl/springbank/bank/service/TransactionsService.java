package pl.springbank.bank.service;



import pl.springbank.bank.entity.Transaction;

import java.util.List;

public interface TransactionsService {
    public List<Transaction> getTransactions();

    public Transaction createTransaction(Transaction transaction);

    public String deleteTransaction(int id);

    public Transaction getTransaction(int id);

    public Transaction updateTransaction(Transaction transaction);

}
