package pl.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bank.dao.AccountsDao;
import pl.bank.entity.Account;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
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
    public String deleteAccount(int id) {
        return accountsDao.deleteAccount(id);
    }

    @Override
    public Account getAccount(int id, int pinNumber) {
        return accountsDao.getAccount(id, pinNumber);
    }

    @Override
    public Account updateAccount(Account account) {
        return accountsDao.updateAccount(account);
    }

    public String transferMoney(int fromAccount, int pinNumber, int destinationAccount, float amount) {
        return accountsDao.transferMoney(fromAccount, pinNumber, destinationAccount, amount);
    }
}
