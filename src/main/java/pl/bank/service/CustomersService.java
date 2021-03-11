package pl.bank.service;

public interface CustomersService {
    public List<Customer> getCustomers();
    public String createCustomer(Customer customer);
    public String deleteCustomer(int number);
    public Account getCustomer(int id);
    public Account updateCustomer(Customer customer);
}
