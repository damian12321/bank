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
        return null;
    }

    @Override
    public String createAccount(Account account) {
        return null;
    }

    @Override
    public String deleteAccount(int number) {
        return null;
    }

    @Override
    public Account getAccount(int id) {
        return null;
    }

    @Override
    public Account updateAccount(Account account) {
        return null;
    }
}
