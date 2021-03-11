package pl.bank.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.bank.entity.Customer;
import pl.bank.exception.CustomException;

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
        Session session = sessionFactory.getCurrentSession();
        customer.setId(0);
        session.save(customer);
        return customer;
    }

    @Override
    public String deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer=session.get(Customer.class,id);
        if (customer == null) {
            throw new CustomException("Customer with id: " + id+" not found.");
        }
        session.delete(customer);
        return "Customer with id: "+id+" has been deleted";
    }

    @Override
    public Customer getCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer=session.get(Customer.class,id);
        if (customer == null) {
            throw new CustomException("Customer with id: " + id+" not found.");
        }
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
        return customer;
    }
}
