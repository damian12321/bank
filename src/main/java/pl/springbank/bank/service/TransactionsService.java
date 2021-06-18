package pl.springbank.bank.service;


import pl.springbank.bank.entity.Transaction;

import java.util.List;

public interface TransactionsService {
    List<Transaction> getTransactions();

    String deleteTransaction(int id);

    Transaction getTransaction(int id);

    Transaction updateTransaction(Transaction transaction);

}
