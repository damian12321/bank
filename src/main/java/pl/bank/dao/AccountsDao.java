package pl.bank.dao;

import pl.bank.entity.Account;

import java.util.List;

public interface AccountsDao {
    public List<Account> getAccounts();

    public Account createAccount(Account account);

    public String deleteAccount(int id);

    public Account getAccount(int id);

    public Account updateAccount(Account account);
}
