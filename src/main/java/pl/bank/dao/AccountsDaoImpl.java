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
    public Account createAccount(Account Account) {
        Session session = sessionFactory.getCurrentSession();
        Account.setId(0);
        session.save(Account);
        return Account;
    }

    @Override
    public String deleteAccount(int id) {
        Session session = sessionFactory.getCurrentSession();
        Account Account = session.get(Account.class, id);
        if (Account == null) {
            throw new CustomException("Account with id: " + id + " not found.");
        }
        session.delete(Account);
        return "Account with id: " + id + " has been deleted.";
    }

    @Override
    public Account getAccount(int id) {
        Session session = sessionFactory.getCurrentSession();
        Account Account = session.get(Account.class, id);
        if (Account == null) {
            throw new CustomException("Account with id: " + id + " not found.");
        }
        return Account;
    }

    @Override
    public Account updateAccount(Account Account) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(Account);
        return Account;
    }

    @Override
    public String transferMoney(int fromAccount, int destinationAccount, float amount) {
        if(fromAccount==destinationAccount||amount<=0)
        {
            throw new CustomException("Incorrect values.");
        }
        Session session = sessionFactory.getCurrentSession();
        Account frAccount = session.get(Account.class, fromAccount);
        if (frAccount == null) {
            throw new CustomException("Account with id: " + fromAccount + " not found.");
        }
        Account toAccount = session.get(Account.class, destinationAccount);
        if (toAccount == null) {
            throw new CustomException("Destination account with id: " + destinationAccount + " not found.");
        }
        float balance1 = frAccount.getBalance();
        float balance2 = toAccount.getBalance();
        if (balance1 - amount < 0) {
            throw new CustomException("Not enough money in the account.");
        }
        frAccount.setBalance(balance1 - amount);
        toAccount.setBalance(balance2 + amount);
        List<Transaction>list1=frAccount.getTransactionList();
        list1.add(new Transaction(TransactionType.OUTGOING_TRANSFER, amount, new Date(), "Outgoing transfer to " + destinationAccount));
        List<Transaction>list2=toAccount.getTransactionList();
        list2.add(new Transaction(TransactionType.INCOMING_TRANSFER, amount, new Date(), "Incoming transfer from " + fromAccount));
        session.save(frAccount);
        session.save(toAccount);
        return "The money has been transferred from " + fromAccount + " to " + destinationAccount + ".";
    }
}
