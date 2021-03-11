package pl.bank.service;

import pl.bank.entity.Customer;

import java.util.List;

public interface CustomersService {
    public List<Customer> getCustomers();

    public Customer createCustomer(Customer customer);

    public String deleteCustomer(int id);

    public Customer getCustomer(int id);

    public Customer updateCustomer(Customer customer);
}
