package pl.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bank.dao.CustomersDao;
import pl.bank.entity.Customer;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomersServiceImpl implements CustomersService {
    private CustomersDao customersDao;

    @Autowired
    public CustomersServiceImpl(CustomersDao customersDao) {
        this.customersDao = customersDao;
    }

    @Override
    public List<Customer> getCustomers() {
        return null;
    }

    @Override
    public String createCustomer(Customer customer) {
        return null;
    }

    @Override
    public String deleteCustomer(int number) {
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
