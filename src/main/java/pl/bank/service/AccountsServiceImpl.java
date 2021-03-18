package pl.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bank.dao.AccountsDao;
import pl.bank.entity.Account;
import pl.bank.exception.NoAccessException;

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
    public Account createAccount(Account account) {
        return accountsDao.createAccount(account);
    }

    @Override
    public String deleteAccount(int accountNumber) {
        return accountsDao.deleteAccount(accountNumber);
    }

    @Override
    public Account getAccount(int accountNumber, int pinNumber) {
        return accountsDao.getAccount(accountNumber, pinNumber);
    }

    @Override
    public Account getAccountByOnlyAccountNumber(int accountNumber) {
        return accountsDao.getAccountByOnlyAccountNumber(accountNumber);
    }

    @Override
    public Account updateAccount(Account account) {
        return accountsDao.updateAccount(account);
    }

    @Override
    public String transferMoney(int fromAccount, int pinNumber, int destinationAccount, float amount) {
        return accountsDao.transferMoney(fromAccount, pinNumber, destinationAccount, amount);
    }

    @Override
    public int getFreeAccountNumber() {
        return accountsDao.getFreeAccountNumber();
    }
}
