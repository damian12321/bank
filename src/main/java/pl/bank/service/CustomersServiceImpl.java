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
        return customersDao.getCustomers();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customersDao.createCustomer(customer);
    }

    @Override
    public String deleteCustomer(int id) {
        return customersDao.deleteCustomer(id);
    }

    @Override
    public Customer getCustomer(int id, String customerPassword) {
        return customersDao.getCustomer(id, customerPassword);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customersDao.updateCustomer(customer);
    }
}
