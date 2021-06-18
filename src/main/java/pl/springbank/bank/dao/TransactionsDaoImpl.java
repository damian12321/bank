package pl.springbank.bank.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.springbank.bank.entity.Transaction;
import pl.springbank.bank.exception.LockedException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TransactionsDaoImpl implements TransactionsDao {

    private EntityManager entityManager;

    @Autowired
    public TransactionsDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Transaction> getTransactions() {

        return entityManager.createQuery("from Transaction ORDER BY date DESC", Transaction.class).getResultList();
    }

    @Override
    public String deleteTransaction(int id) {

        Transaction transaction = entityManager.find(Transaction.class, id);
        if (transaction == null) {
            throw new LockedException("Transaction with id: " + id + " not found.");
        }
        entityManager.remove(transaction);
        return "Transaction with id: " + id + " has been deleted.";
    }

    @Override
    public Transaction getTransaction(int id) {

        Transaction transaction = entityManager.find(Transaction.class, id);
        if (transaction == null) {
            throw new LockedException("Transaction with id: " + id + " not found.");
        }
        return transaction;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {

        Transaction tempTransaction = entityManager.find(Transaction.class, transaction.getId());
        if (tempTransaction != null) {
            Query query = entityManager.createQuery("UPDATE Transaction t SET " +
                    "t.date=:date,t.description=:description,t.transactionType=:transactionType WHERE t.id=:id");
            query.setParameter("date", transaction.getDate());
            query.setParameter("description", transaction.getDescription());
            query.setParameter("transactionType", transaction.getTransactionType());
            query.setParameter("id", transaction.getId());
            query.executeUpdate();
        } else {
            throw new LockedException("Transaction with id: " + transaction.getId() + " not found.");
        }
        return tempTransaction;
    }
}
