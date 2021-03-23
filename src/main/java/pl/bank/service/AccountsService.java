package pl.bank.service;

import pl.bank.entity.Account;

import java.util.List;

public interface AccountsService {
    public List<Account> getAccounts();

    public Account createAccount(Account account);

    public String deleteAccount(int accountId);

    public Account getAccount(int accountNumber, int pinNumber);

    public Account getAccountByOnlyAccountNumber(int accountNumber);

    public Account updateAccount(Account account);

    public String transferMoney(int fromAccount, int pinNumber, int destinationAccount, float amount, String description);

    public int getFreeAccountNumber();

    public String depositMoney(int accountNumber, int pinNumber, float amount);

    public String withdrawMoney(int accountNumber, int pinNumber, float amount);
}
