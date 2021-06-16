package pl.springbank.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.springbank.bank.dao.AccountsDao;
import pl.springbank.bank.entity.Account;
import pl.springbank.bank.entity.Transaction;
import pl.springbank.bank.exception.NoAccessException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional(dontRollbackOn = {NoAccessException.class})
public class AccountsServiceImpl implements AccountsService {

    private AccountsDao accountsDao;

    @Autowired
    public AccountsServiceImpl(AccountsDao accountsDao) {
        this.accountsDao = accountsDao;
    }

    @Override
    public List<Account> getAccounts() {
        return accountsDao.getAccounts();
    }

    @Override
    public String deleteAccount(int accountId) {
        return accountsDao.deleteAccount(accountId);
    }

    @Override
    public Account getAccount(int accountId, String password) {
        return accountsDao.getAccount(accountId, password);
    }

    @Override
    public Account getAccountByAccountNumber(int accountNumber) {
        return accountsDao.getAccountByAccountNumber(accountNumber);
    }

    @Override
    public Account getAccountByAccountId(int accountId) {
        return accountsDao.getAccountByAccountId(accountId);
    }

    @Override
    public Account createAccount(Account account) {
        return accountsDao.createAccount(account);
    }

    @Override
    public Account saveOrUpdateAccount(Account account) {
        return accountsDao.saveOrUpdateAccount(account);
    }

    @Override
    public String transferMoney(int fromAccount, int pinNumber, int destinationAccount, float amount, String description) {
        return accountsDao.transferMoney(fromAccount, pinNumber, destinationAccount, amount, description);
    }

    @Override
    public int getAvailableAccountNumber() {
        return accountsDao.getAvailableAccountNumber();
    }

    @Override
    public String depositMoney(int accountNumber, int pinNumber, float amount) {
        return accountsDao.depositMoney(accountNumber, pinNumber, amount);
    }

    @Override
    public String withdrawMoney(int accountNumber, int pinNumber, float amount) {
        return accountsDao.withdrawMoney(accountNumber, pinNumber, amount);
    }

    @Override
    public List<Transaction> getAccountsTransactions(int accountId, String password) {
        return accountsDao.getAccountsTransactions(accountId, password);
    }
}
