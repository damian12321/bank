package pl.springbank.bank.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.springbank.bank.entity.Transaction;
import pl.springbank.bank.exception.LockedException;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TransactionsDaoImpl implements TransactionsDao {

    private EntityManager entityManager;
    private Session session;

    @Autowired
    public TransactionsDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.session = entityManager.unwrap(Session.class);
    }

    @Override
    public List<Transaction> getTransactions() {

        Query<Transaction> query = session.createQuery("from Transaction ORDER BY date DESC", Transaction.class);
        List<Transaction> list = query.getResultList();
        return list;
    }

    @Override
    public String deleteTransaction(int id) {

        Transaction transaction = session.get(Transaction.class, id);
        if (transaction == null) {
            throw new LockedException("Transaction with id: " + id + " not found.");
        }
        session.delete(transaction);
        return "Transaction with id: " + id + " has been deleted.";
    }

    @Override
    public Transaction getTransaction(int id) {

        Transaction transaction = session.get(Transaction.class, id);
        if (transaction == null) {
            throw new LockedException("Transaction with id: " + id + " not found.");
        }
        return transaction;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {

        session.update(transaction);
        return transaction;
    }
}
