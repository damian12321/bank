package pl.bank.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.bank.entity.Account;
import pl.bank.exception.CustomException;

import java.util.List;

@Repository
public class AccountsDaoImpl implements AccountsDao {

    private SessionFactory sessionFactory;

    @Autowired
    public AccountsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Account> getAccounts() {
        Session session = sessionFactory.getCurrentSession();
        Query<Account> query = session.createQuery("from Account", Account.class);
        List<Account> list = query.getResultList();
        return list;
    }

    @Override
    public Account createAccount(Account Account) {
        Session session = sessionFactory.getCurrentSession();
        Account.setId(0);
        session.save(Account);
        return Account;
    }

    @Override
    public String deleteAccount(int id) {
        Session session = sessionFactory.getCurrentSession();
        Account Account=session.get(Account.class,id);
        if (Account == null) {
            throw new CustomException("Account with id: " + id+" not found.");
        }
        session.delete(Account);
        return "Account with id: "+id+" has been deleted.";
    }

    @Override
    public Account getAccount(int id) {
        Session session = sessionFactory.getCurrentSession();
        Account Account=session.get(Account.class,id);
        if (Account == null) {
            throw new CustomException("Account with id: " + id+" not found.");
        }
        return Account;
    }

    @Override
    public Account updateAccount(Account Account) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(Account);
        return Account;
    }
}
