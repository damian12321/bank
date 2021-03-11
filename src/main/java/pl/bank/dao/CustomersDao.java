package pl.bank.dao;

import pl.bank.entity.Customer;

import java.util.List;

public interface CustomersDao {
    public List<Customer> getCustomers();

    public Customer createCustomer(Customer customer);

    public String deleteCustomer(int id);

    public Customer getCustomer(int id);

    public Customer updateCustomer(Customer customer);
}
