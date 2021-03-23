package pl.bank.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.bank.entity.Transaction;
import pl.bank.exception.CustomException;

import java.util.List;

@Repository
public class TransactionsDaoImpl implements TransactionsDao {

    private SessionFactory sessionFactory;

    @Autowired
    public TransactionsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Transaction> getTransactions() {
        Session session = sessionFactory.getCurrentSession();
        Query<Transaction> query = session.createQuery("from Transaction ORDER BY date DESC", Transaction.class);
        List<Transaction> list = query.getResultList();
        return list;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        transaction.setId(0);
        session.save(transaction);
        return transaction;
    }

    @Override
    public String deleteTransaction(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.get(Transaction.class, id);
        if (transaction == null) {
            throw new CustomException("Transaction with id: " + id + " not found.");
        }
        session.delete(transaction);
        return "Transaction with id: " + id + " has been deleted.";
    }

    @Override
    public Transaction getTransaction(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.get(Transaction.class, id);
        if (transaction == null) {
            throw new CustomException("Transaction with id: " + id + " not found.");
        }
        return transaction;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.update(transaction);
        return transaction;
    }
}
