package pl.springbank.bank.service;


import pl.springbank.bank.entity.Account;
import pl.springbank.bank.entity.Transaction;

import java.util.List;

public interface AccountsService {
    List<Account> getAccounts();

    String deleteAccount(int accountId);

    Account getAccount(int accountId, String password);

    Account getAccountByAccountNumber(int accountNumber);

    Account getAccountByAccountId(int accountId);

    Account saveOrUpdateAccount(Account account);

    Account createAccount(Account account);

    String transferMoney(int fromAccount, int pinNumber, int destinationAccount, float amount, String description);

    int getAvailableAccountNumber();

    String depositMoney(int accountNumber, int pinNumber, float amount);

    String withdrawMoney(int accountNumber, int pinNumber, float amount);

    List<Transaction> getAccountsTransactions(int accountId, String password);
}
