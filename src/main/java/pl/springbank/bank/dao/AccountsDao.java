package pl.springbank.bank.dao;

import pl.springbank.bank.entity.Account;

import java.util.List;

public interface AccountsDao {
    public List<Account> getAccounts();

    public String deleteAccount(int accountId);

    public Account getAccount(int accountNumber, int pinNumber);

    public Account getAccountByOnlyAccountNumber(int accountNumber);

    public Account updateAccount(Account account);

    public String transferMoney(int fromAccount, int pinNumber, int destinationAccount, float amount, String description);

    public int getAvailableAccountNumber();

    public String depositMoney(int accountNumber, int pinNumber, float amount);

    public String withdrawMoney(int accountNumber, int pinNumber, float amount);
}
