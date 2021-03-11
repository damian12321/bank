package pl.bank.service;

public interface TransactionsService {
    public List<Transaction> getTransactions();
    public String createTransaction(Transaction transaction);
    public String deleteTransaction(int number);
    public Account getTransaction(int id);
    public Account updateTransaction(Transaction transaction);
}
