package pl.bank.dao;

public interface AccountsDao {
    public List<Account> getAccounts();
    public String createAccount(Account account);
    public String deleteAccount(int number);
    public Account getAccount(int id);
    public Account updateAccount(Account account);
}
