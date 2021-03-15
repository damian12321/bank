package pl.bank.dao;

import pl.bank.entity.Account;

import java.util.List;

public interface AccountsDao {
    public List<Account> getAccounts();

    public Account createAccount(Account account);

    public String deleteAccount(int accountNumber);

    public Account getAccount(int accountNumber, int pinNumber);

    public Account updateAccount(Account account);

    public String transferMoney(int fromAccount, int pinNumber, int destinationAccount, float amount);
}
