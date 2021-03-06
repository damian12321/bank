package pl.springbank.bank.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.springbank.bank.entity.Account;
import pl.springbank.bank.entity.Transaction;
import pl.springbank.bank.enums.TransactionType;
import pl.springbank.bank.exception.CustomException;
import pl.springbank.bank.exception.LockedException;
import pl.springbank.bank.exception.NoAccessException;
import pl.springbank.bank.exception.NoResourcesException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class AccountsDaoImpl implements AccountsDao {

    private EntityManager entityManager;

    @Autowired
    public AccountsDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Account> getAccounts() {
        return entityManager.createQuery("from Account", Account.class).getResultList();
    }

    @Override
    public String deleteAccount(int accountId) {
        Account account = entityManager.find(Account.class, accountId);
        if (account == null) {
            throw new NoResourcesException("Account with number: " + accountId + " not found.");
        }
        entityManager.remove(account);
        return "Account with id: " + accountId + " has been deleted.";
    }

    @Override
    public Account getAccount(int accountId, String password) {
        Account account=checkAccountByAccountIdAndReturnAccount(accountId,password);
        account.setLoginAttempts(3);
        return account;
    }

    @Override
    public Account getAccountByAccountNumber(int accountNumber) {
        List<Account> list = entityManager.createQuery("from Account a where a.accountNumber=" + accountNumber).getResultList();
        return list.get(0);
    }

    @Override
    public Account getAccountByAccountId(int accountId) {
        List<Account> list = entityManager.createQuery("from Account a where a.id=" + accountId).getResultList();
        return list.get(0);
    }

    @Override
    public Account saveOrUpdateAccount(Account account) {
        Account tempAccount = entityManager.find(Account.class, account.getId());
        if (tempAccount != null) {
            Query query = entityManager.createQuery("UPDATE Account a SET a.accountNumber = :accountNumber, " +
                    "a.balance=:balance,a.firstName=:firstName,a.lastName=:lastName,a.isActive=:isActive," +
                    "a.loginAttempts=:loginAttempts WHERE a.id=:id");
            query.setParameter("accountNumber", account.getAccountNumber());
            query.setParameter("balance", account.getBalance());
            query.setParameter("firstName", account.getFirstName());
            query.setParameter("lastName", account.getLastName());
            query.setParameter("isActive", account.getIsActive());
            query.setParameter("loginAttempts", account.getLoginAttempts());
            query.setParameter("id", account.getId());
            query.executeUpdate();

        } else {
            account.setId(0);
            account.setIsActive(true);
            account.setLoginAttempts(3);
            account.setAccountNumber(getAvailableAccountNumber());
            try {
                entityManager.persist(account);
            }catch (PersistenceException e)
            {
                throw new CustomException("An account with this email or account number already exists.");
            }
        }
        return account;
    }

    @Override
    public Account createAccount(Account account) {
        account.setId(0);
        account.setIsActive(true);
        account.setLoginAttempts(3);
        account.setAccountNumber(getAvailableAccountNumber());
        try {
            entityManager.persist(account);
        }catch (PersistenceException e)
        {
            throw new CustomException("An account with this email or account number already exists.");
        }
        return account;
    }

    @Override
    public String transferMoney(int fromAccount, int pinNumber, int destinationAccount, float amount, String description) {
        String descriptionOut = "Outgoing transfer to account " + destinationAccount + ".";
        String descriptionIn = "Incoming transfer from account " + fromAccount + ".";
        if (fromAccount == destinationAccount || amount <= 0) {
            throw new CustomException("Incorrect values.");
        }
        checkAccountByAccountNumberAndReturnAccount(fromAccount, pinNumber);
        Account frAccount = checkAccountByAccountNumberAndReturnAccount(fromAccount, pinNumber);
        List<Account> list = entityManager.createQuery("from Account where accountNumber =" + destinationAccount).getResultList();
        if (list.isEmpty()) {
            throw new NoResourcesException("Destination account with number: " + destinationAccount + " not found.");
        }
        Account toAccount = list.get(0);
        float balance1 = frAccount.getBalance();
        float balance2 = toAccount.getBalance();
        if (balance1 - amount < 0) {
            throw new CustomException("Not enough money in the account.");
        }
        frAccount.setBalance(balance1 - amount);
        toAccount.setBalance(balance2 + amount);
        Transaction transactionOut;
        Transaction transactionIn;

        if (!description.isEmpty()) {
            transactionOut = new Transaction(TransactionType.OUTGOING_TRANSFER, amount, new Date(), description
                    + ". Send to account " + destinationAccount);
            transactionIn = new Transaction(TransactionType.INCOMING_TRANSFER, amount, new Date(), description
                    + ". Send from account " + fromAccount);
        } else {
            transactionOut = new Transaction(TransactionType.OUTGOING_TRANSFER, amount, new Date(), descriptionOut);
            transactionIn = new Transaction(TransactionType.INCOMING_TRANSFER, amount, new Date(), descriptionIn);
        }
        transactionOut.setAccount(frAccount);
        transactionIn.setAccount(toAccount);
        entityManager.persist(transactionIn);
        entityManager.persist(transactionOut);
        return "The money has been transferred from " + fromAccount + " to " + destinationAccount + ".";
    }

    @Override
    public String depositMoney(int accountNumber, int pinNumber, float amount) {
        String description = "Deposit to account " + accountNumber;
        if (amount <= 0) {
            throw new CustomException("Incorrect values.");
        }

        Account account = checkAccountByAccountNumberAndReturnAccount(accountNumber, pinNumber);
        float balance = account.getBalance();
        account.setBalance(balance + amount);
        List<Transaction> list1 = account.getTransactionList();
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, new Date(), description);
        transaction.setAccount(account);
        list1.add(transaction);
        account.setLoginAttempts(3);
        return "Deposit has been completed.";
    }

    @Override
    public String withdrawMoney(int accountNumber, int pinNumber, float amount) {
        String description = "Withdrawal from account " + accountNumber;
        if (amount <= 0) {
            throw new CustomException("Incorrect values.");
        }

        Account account = checkAccountByAccountNumberAndReturnAccount(accountNumber, pinNumber);
        float balance = account.getBalance();
        if (balance - amount < 0) {
            throw new CustomException("Not enough money in the account.");
        }
        account.setBalance(balance - amount);
        List<Transaction> list1 = account.getTransactionList();
        Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, amount, new Date(), description);
        transaction.setAccount(account);
        list1.add(transaction);
        account.setLoginAttempts(3);
        return "Withdrawal has been completed.";
    }

    @Override
    public List<Transaction> getAccountsTransactions(int accountId, String password) {
        Account account=checkAccountByAccountIdAndReturnAccount(accountId,password);
        return account.getTransactionList();
    }

    private Account checkAccountByAccountNumberAndReturnAccount(int accountNumber, int pinNumber) {
        List<Account> list = entityManager.createQuery("from Account where accountNumber =" + accountNumber).getResultList();
        if (list.isEmpty()) {
            throw new NoResourcesException("Account with number: " + accountNumber + " not found.");
        }
        Account account = list.get(0);

        if (!account.getIsActive()) {
            throw new LockedException("Account with number " + accountNumber + " is not active.");
        }
        if (account.getPinNumber() != pinNumber) {
            throw new NoAccessException("Pin number is incorrect.");
        }
        return account;
    }

    private Account checkAccountByAccountIdAndReturnAccount(int accountId, String password) {
        Account account=entityManager.find(Account.class,accountId);
        if (account==null) {
            throw new NoResourcesException("Account with id: " + accountId + " not found.");
        }
        if (!account.getIsActive()) {
            throw new LockedException("Account with id " + accountId + " is not active.");
        }
        if (!account.getPassword().equals(password)) {
            throw new NoAccessException("Password is incorrect.");
        }
        return account;
    }

    public int getAvailableAccountNumber() {
        List<Account> list = entityManager.createQuery("from Account", Account.class).getResultList();
        int highestNumber = 1;
        for (Account account : list) {
            if (account.getAccountNumber() > highestNumber) {
                highestNumber = account.getAccountNumber();
            }
        }
        return ++highestNumber;
    }

    @Override
    public String changePassword(int id, String oldPassword, String newPassword) {
        Account account=checkAccountByAccountIdAndReturnAccount(id,oldPassword);
        account.setPassword(newPassword);
        return "Password has been changed.";
    }

    @Override
    public String changePinNumber(int accountNumber, int oldPin, int newPin) {
        Account account=checkAccountByAccountNumberAndReturnAccount(accountNumber,oldPin);
        account.setPinNumber(newPin);
        return "Pin number has been changed";
    }
}
