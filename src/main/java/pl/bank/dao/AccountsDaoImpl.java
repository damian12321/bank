package pl.bank.dao;

import org.springframework.stereotype.Repository;
import pl.bank.entity.Account;

import java.util.List;

@Repository
public class AccountsDaoImpl implements AccountsDao {
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
