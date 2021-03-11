package pl.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bank.entity.Customer;
import pl.bank.service.AccountsService;
import pl.bank.service.CustomersService;
import pl.bank.service.TransactionsService;
import pl.bank.service.TransfersService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankController {
    private AccountsService accountsService;
    private CustomersService customersService;
    private TransfersService transfersService;
    private TransactionsService transactionsService;

    @Autowired
    public BankController(AccountsService accountsService, CustomersService customersService, TransfersService transfersService, TransactionsService transactionsService) {
        this.accountsService = accountsService;
        this.customersService = customersService;
        this.transfersService = transfersService;
        this.transactionsService = transactionsService;
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
