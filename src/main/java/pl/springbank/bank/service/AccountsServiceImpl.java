package pl.springbank.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.springbank.bank.dao.AccountsDao;
import pl.springbank.bank.entity.Account;
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
    public String transferMoney(int fromAccount, int pinNumber, int destinationAccount, float amount, String description) {
        return accountsDao.transferMoney(fromAccount, pinNumber, destinationAccount, amount, description);
    }

    @Override
    public int getFreeAccountNumber() {
        return accountsDao.getFreeAccountNumber();
    }

    @Override
    public String depositMoney(int accountNumber, int pinNumber, float amount) {
        return accountsDao.depositMoney(accountNumber, pinNumber, amount);
    }

    @Override
    public String withdrawMoney(int accountNumber, int pinNumber, float amount) {
        return accountsDao.withdrawMoney(accountNumber, pinNumber, amount);
    }
}
