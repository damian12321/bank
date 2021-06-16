package pl.springbank.bank.dao;

import pl.springbank.bank.entity.Account;
import pl.springbank.bank.entity.Transaction;
import java.util.List;

public interface AccountsDao {

    public List<Account> getAccounts();

    public String deleteAccount(int accountId);

    public Account getAccount(int accountId, String password);

    public Account getAccountByAccountNumber(int accountNumber);

    public Account getAccountByAccountId(int accountId);

    public Account saveOrUpdateAccount(Account account);

    public Account createAccount(Account account);

    public String transferMoney(int fromAccount, int pinNumber, int destinationAccount, float amount, String description);

    public int getAvailableAccountNumber();

    public String depositMoney(int accountNumber, int pinNumber, float amount);

    public String withdrawMoney(int accountNumber, int pinNumber, float amount);

    public List<Transaction> getAccountsTransactions(int accountId, String password);
}
