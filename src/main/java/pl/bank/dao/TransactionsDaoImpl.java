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
        Query<Transaction> query = session.createQuery("from Transaction", Transaction.class);
        List<Transaction> list = query.getResultList();
        return list;
    }

    @Override
    public Transaction createTransaction(Transaction Transaction) {
        Session session = sessionFactory.getCurrentSession();
        Transaction.setId(0);
        session.save(Transaction);
        return Transaction;
    }

    @Override
    public String deleteTransaction(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction Transaction = session.get(Transaction.class, id);
        if (Transaction == null) {
            throw new CustomException("Transaction with id: " + id + " not found.");
        }
        session.delete(Transaction);
        return "Transaction with id: " + id + " has been deleted";
    }

    @Override
    public Transaction getTransaction(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction Transaction = session.get(Transaction.class, id);
        if (Transaction == null) {
            throw new CustomException("Transaction with id: " + id + " not found.");
        }
        return Transaction;
    }

    @Override
    public Transaction updateTransaction(Transaction Transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(Transaction);
        return Transaction;
    }
}
