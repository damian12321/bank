package pl.bank.dao;

public interface TransactionsDao {
    public List<Transaction> getTransactions();
    public String createTransaction(Transaction transaction);
    public String deleteTransaction(int number);
    public Account getTransaction(int id);
    public Account updateTransaction(Transaction transaction);
}
