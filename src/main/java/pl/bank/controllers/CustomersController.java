package pl.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import pl.bank.entity.Customer;
import pl.bank.service.CustomersService;


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

    @GetMapping("/customer")
    public List<Customer> getCustomers() {
        return customersService.getCustomers();
    }

    @GetMapping("/customer/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        return customersService.getCustomer(customerId);
    }

    @DeleteMapping("/customer/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {
        return customersService.deleteCustomer(customerId);
    }

    @PutMapping("/customer")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customersService.updateCustomer(customer);
    }

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customersService.createCustomer(customer);
    }

}
