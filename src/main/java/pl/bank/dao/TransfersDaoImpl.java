package pl.bank.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.bank.entity.Transfer;
import pl.bank.exception.CustomException;

import java.util.List;

@Repository
public class TransfersDaoImpl implements TransfersDao {

    private SessionFactory sessionFactory;

    @Autowired
    public TransfersDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Transfer> getTransfers() {
        Session session = sessionFactory.getCurrentSession();
        Query<Transfer> query = session.createQuery("from Transfer", Transfer.class);
        List<Transfer> list = query.getResultList();
        return list;
    }

    @Override
    public Transfer createTransfer(Transfer Transfer) {
        Session session = sessionFactory.getCurrentSession();
        Transfer.setId(0);
        session.save(Transfer);
        return Transfer;
    }

    @Override
    public String deleteTransfer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transfer Transfer=session.get(Transfer.class,id);
        if (Transfer == null) {
            throw new CustomException("Transfer with id: " + id+" not found.");
        }
        session.delete(Transfer);
        return "Transfer with id: "+id+" has been deleted";
    }

    @Override
    public Transfer getTransfer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transfer Transfer=session.get(Transfer.class,id);
        if (Transfer == null) {
            throw new CustomException("Transfer with id: " + id+" not found.");
        }
        return Transfer;
    }

    @Override
    public Transfer updateTransfer(Transfer Transfer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(Transfer);
        return Transfer;
    }
}
