package pl.springbank.bank.dao;

import pl.springbank.bank.entity.Customer;

import java.util.List;

public interface CustomersDao {
    public List<Customer> getCustomers();

    public Customer createCustomer(Customer customer);

    public String deleteCustomer(int id);

    public Customer getCustomer(int id, String customerPassword);

    public Customer updateCustomer(Customer customer);
}
