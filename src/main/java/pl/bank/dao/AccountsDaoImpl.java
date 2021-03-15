package pl.bank.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.bank.entity.Account;
import pl.bank.entity.Transaction;
import pl.bank.enums.TransactionType;
import pl.bank.exception.CustomException;

import java.util.Date;
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
    public Account createAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();
        account.setId(0);
        session.save(account);
        return account;
    }

    @Override
    public String deleteAccount(int id) {
        Session session = sessionFactory.getCurrentSession();
        Account account = session.get(Account.class, id);
        if (account == null) {
            throw new CustomException("Account with id: " + id + " not found.");
        }
        session.delete(account);
        return "Account with id: " + id + " has been deleted.";
    }

    @Override
    public Account getAccount(int id, int pinNumber) {
        Session session = sessionFactory.getCurrentSession();
        Account account = session.get(Account.class, id);
        if (account == null) {
            throw new CustomException("Account with id: " + id + " not found.");
        }
        if (!account.isActive()) {
            throw new CustomException("Account with id number " + id + " is not active.");
        }
        if (account.getPinNumber() != pinNumber) {
            int loginAttempts = account.getLoginAttempts();
            if (loginAttempts > 0) {
                account.setLoginAttempts(--loginAttempts);
                if (loginAttempts == 0) {
                    account.setActive(false);
                }
                session.save(account);
            }
            return null;
        }
        return account;
    }

    @Override
    public Account updateAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(account);
        return account;
    }

    @Override
    public String transferMoney(int fromAccount, int pinNumber, int destinationAccount, float amount) {
        if (fromAccount == destinationAccount || amount <= 0) {
            throw new CustomException("Incorrect values.");
        }
        Session session = sessionFactory.getCurrentSession();
        Query<Account> query = session.createQuery("from Account where accountNumber =" + fromAccount);
        if (query.list().isEmpty()) {
            throw new CustomException("Account with number: " + fromAccount + " not found.");
        }
        Account frAccount = query.getResultList().get(0);

        if (!frAccount.isActive()) {
            throw new CustomException("Account with number " + fromAccount + " is not active.");
        }
        if (frAccount.getPinNumber() != pinNumber) {
            int loginAttempts = frAccount.getLoginAttempts();
            if (loginAttempts > 0) {
                frAccount.setLoginAttempts(--loginAttempts);
                if (loginAttempts == 0) {
                    frAccount.setActive(false);
                }
                session.save(frAccount);
            }
            return null;
        }
        query = session.createQuery("from Account where accountNumber =" + destinationAccount);
        if (query.list().isEmpty()) {
            throw new CustomException("Destination account with number: " + destinationAccount + " not found.");
        }
        Account toAccount = query.getResultList().get(0);
        float balance1 = frAccount.getBalance();
        float balance2 = toAccount.getBalance();
        if (balance1 - amount < 0) {
            throw new CustomException("Not enough money in the account.");
        }
        frAccount.setBalance(balance1 - amount);
        toAccount.setBalance(balance2 + amount);
        List<Transaction> list1 = frAccount.getTransactionList();
        list1.add(new Transaction(TransactionType.OUTGOING_TRANSFER, amount, new Date(), "Outgoing transfer to account " + destinationAccount + "."));
        List<Transaction> list2 = toAccount.getTransactionList();
        list2.add(new Transaction(TransactionType.INCOMING_TRANSFER, amount, new Date(), "Incoming transfer from account " + fromAccount + "."));
        session.save(frAccount);
        session.save(toAccount);
        return "The money has been transferred from " + fromAccount + " to " + destinationAccount + ".";
    }

}
