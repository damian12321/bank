package pl.bank.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.bank.entity.Customer;

import java.util.List;

@Repository
public class CustomersDaoImpl implements CustomersDao {

    private SessionFactory sessionFactory;

    @Autowired
    public CustomersDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getCustomers() {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("from Customer", Customer.class);
        List<Customer> list = query.getResultList();
        return list;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public String deleteCustomer(int id) {
        return null;
    }

    @Override
    public Customer getCustomer(int id) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }
}
