package pl.springbank.bank.dao;

import pl.springbank.bank.entity.Transaction;

import java.util.List;

public interface TransactionsDao {

    List<Transaction> getTransactions();

    String deleteTransaction(int id);

    Transaction getTransaction(int id);

    Transaction updateTransaction(Transaction transaction);

}
