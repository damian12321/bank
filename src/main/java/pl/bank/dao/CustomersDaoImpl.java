package pl.bank.dao;

import org.springframework.stereotype.Repository;
import pl.bank.entity.Customer;

import java.util.List;

@Repository
public class CustomersDaoImpl implements CustomersDao {
    @Override
    public List<Customer> getCustomers() {
        return null;
    }

    @Override
    public String createCustomer(Customer customer) {
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
