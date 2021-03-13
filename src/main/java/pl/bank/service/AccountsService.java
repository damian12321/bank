package pl.bank.service;

import pl.bank.entity.Account;

import java.util.List;

public interface AccountsService {
    public List<Account> getAccounts();

    public Account createAccount(Account account);

    public String deleteAccount(int id);

    public Account getAccount(int id);

    public Account updateAccount(Account account);

    public String transferMoney(int fromAccount,int pinNumber, int destinationAccount, float amount);
}
