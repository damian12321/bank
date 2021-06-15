package pl.springbank.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import pl.springbank.bank.entity.Customer;
import pl.springbank.bank.service.CustomersService;

import javax.validation.Valid;
import java.util.List;

@RestController
@EnableTransactionManagement
@RequestMapping("/api")
public class CustomersController {

    private CustomersService customersService;

    @Autowired
    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customersService.getCustomers();
    }

    @GetMapping("/customers/{customerId}/{customerPassword}")
    public Customer getCustomer(@PathVariable int customerId, @PathVariable String customerPassword) {
        return customersService.getCustomer(customerId, customerPassword);
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {
        return customersService.deleteCustomer(customerId);
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@Valid @RequestBody Customer customer) {
        return customersService.updateCustomer(customer);
    }

    @PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customersService.createCustomer(customer);
    }

}
