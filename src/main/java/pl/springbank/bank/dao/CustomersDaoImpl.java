package pl.springbank.bank.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.springbank.bank.entity.Customer;
import pl.springbank.bank.exception.CustomException;
import pl.springbank.bank.exception.NoAccessException;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class CustomersDaoImpl implements CustomersDao {

    private EntityManager entityManager;
    private Session session;


    @Autowired
    public CustomersDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        session = entityManager.unwrap(Session.class);
    }

    @Override
    public List<Customer> getCustomers() {

        Query<Customer> query = session.createQuery("from Customer", Customer.class);
        List<Customer> list = query.getResultList();
        return list;
    }

    @Override
    public Customer createCustomer(Customer customer) {

        customer.setId(0);
        session.save(customer);
        return customer;
    }

    @Override
    public String deleteCustomer(int id) {

        Customer customer = session.get(Customer.class, id);
        if (customer == null) {
            throw new CustomException("Customer with id: " + id + " not found.");
        }
        session.delete(customer);
        return "Customer with id: " + id + " has been deleted.";
    }

    @Override
    public Customer getCustomer(int id, String customerPassword) {

        Customer customer = session.get(Customer.class, id);
        if (customer == null) {
            throw new CustomException("Customer with id: " + id + " not found.");
        }
        if (!customer.getPassword().equals(customerPassword)) {
            throw new NoAccessException("Password is not correct.");
        }
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {

        session.update(customer);
        return customer;
    }
}
