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
import pl.bank.exception.NoAccessException;

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
    public String deleteAccount(int accountNumber) {
        Session session = sessionFactory.getCurrentSession();
        Query<Account> query = session.createQuery("from Account where accountNumber =" + accountNumber);
        if (query.list().isEmpty()) {
            throw new CustomException("Account with number: " + accountNumber + " not found.");
        }
        Account account = query.getResultList().get(0);
        session.delete(account);
        return "Account with number: " + accountNumber + " has been deleted.";
    }

    @Override
    public Account getAccount(int accountNumber, int pinNumber) {
        Session session = sessionFactory.getCurrentSession();
        Query<Account> query = session.createQuery("from Account where accountNumber =" + accountNumber);
        if (query.list().isEmpty()) {
            throw new CustomException("Account with number: " + accountNumber + " not found.");
        }
        Account account = query.getResultList().get(0);
        if (!account.getIsActive()) {
            throw new CustomException("Account with number " + accountNumber + " is not active.");
        }
        if (account.getPinNumber() != pinNumber) {
            throw new NoAccessException("Pin number is incorrect.");
        }
        account.setLoginAttempts(3);
        session.save(account);
        return account;
    }

    @Override
    public Account getAccountByOnlyAccountNumber(int accountNumber) {
        Session session = sessionFactory.getCurrentSession();
        Query<Account> query = session.createQuery("from Account where accountNumber =" + accountNumber);
        Account account = query.getResultList().get(0);
        return account;
    }

    @Override
    public Account updateAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(account);
        return account;
    }

    @Override
    public String transferMoney(int fromAccount, int pinNumber, int destinationAccount, float amount, String description) {
        String descriptionOut = "Outgoing transfer to account " + destinationAccount + ".";
        String descriptionIn = "Incoming transfer from account " + fromAccount + ".";
        if (fromAccount == destinationAccount || amount <= 0) {
            throw new CustomException("Incorrect values.");
        }
        Session session = sessionFactory.getCurrentSession();
        Query<Account> query = session.createQuery("from Account where accountNumber =" + fromAccount);
        if (query.list().isEmpty()) {
            throw new CustomException("Account with number: " + fromAccount + " not found.");
        }
        Account frAccount = query.getResultList().get(0);

        if (!frAccount.getIsActive()) {
            throw new CustomException("Account with number " + fromAccount + " is not active.");
        }
        if (frAccount.getPinNumber() != pinNumber) {
            throw new NoAccessException("Pin number is incorrect.");
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
        Transaction transactionOut;
        Transaction transactionIn;
        List<Transaction> list1 = frAccount.getTransactionList();
        if (!description.isEmpty()) {
            transactionOut = new Transaction(TransactionType.OUTGOING_TRANSFER, amount, new Date(), description);
            transactionIn = new Transaction(TransactionType.INCOMING_TRANSFER, amount, new Date(), description);
        } else {
            transactionOut = new Transaction(TransactionType.OUTGOING_TRANSFER, amount, new Date(), descriptionOut);
            transactionIn = new Transaction(TransactionType.INCOMING_TRANSFER, amount, new Date(), descriptionIn);
        }
        list1.add(transactionOut);
        List<Transaction> list2 = toAccount.getTransactionList();
        list2.add(transactionIn);
        frAccount.setLoginAttempts(3);
        session.save(frAccount);
        session.save(toAccount);
        return "The money has been transferred from " + fromAccount + " to " + destinationAccount + ".";
    }

    @Override
    public int getFreeAccountNumber() {
        Session session = sessionFactory.getCurrentSession();
        Query<Account> query = session.createQuery("from Account", Account.class);
        List<Account> list = query.getResultList();
        int highestNumber = 1;
        for (Account account : list) {
            if (account.getAccountNumber() > highestNumber) {
                highestNumber = account.getAccountNumber();
            }
        }
        return ++highestNumber;
    }

    @Override
    public String depositMoney(int accountNumber, int pinNumber, float amount) {
        String description = "Deposit";
        if (amount <= 0) {
            throw new CustomException("Incorrect values.");
        }
        Session session = sessionFactory.getCurrentSession();
        Query<Account> query = session.createQuery("from Account where accountNumber =" + accountNumber);
        if (query.list().isEmpty()) {
            throw new CustomException("Account with number: " + accountNumber + " not found.");
        }
        Account account = query.getResultList().get(0);

        if (!account.getIsActive()) {
            throw new CustomException("Account with number " + accountNumber + " is not active.");
        }
        if (account.getPinNumber() != pinNumber) {
            throw new NoAccessException("Pin number is incorrect.");
        }
        float balance = account.getBalance();
        account.setBalance(balance + amount);
        List<Transaction> list1 = account.getTransactionList();
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, new Date(), description);
        list1.add(transaction);
        account.setLoginAttempts(3);
        session.save(account);
        return "Deposit has been completed.";
    }

    @Override
    public String withdrawMoney(int accountNumber, int pinNumber, float amount) {
        String description = "Withdrawal";
        if (amount <= 0) {
            throw new CustomException("Incorrect values.");
        }
        Session session = sessionFactory.getCurrentSession();
        Query<Account> query = session.createQuery("from Account where accountNumber =" + accountNumber);
        if (query.list().isEmpty()) {
            throw new CustomException("Account with number: " + accountNumber + " not found.");
        }
        Account account = query.getResultList().get(0);

        if (!account.getIsActive()) {
            throw new CustomException("Account with number " + accountNumber + " is not active.");
        }
        if (account.getPinNumber() != pinNumber) {
            throw new NoAccessException("Pin number is incorrect.");
        }
        float balance = account.getBalance();
        if (balance - amount < 0) {
            throw new CustomException("Not enough money in the account.");
        }
        account.setBalance(balance - amount);
        List<Transaction> list1 = account.getTransactionList();
        Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, amount, new Date(), description);
        list1.add(transaction);
        account.setLoginAttempts(3);
        session.save(account);
        return "Withdrawal has been completed.";
    }


}
