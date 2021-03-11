package pl.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import pl.bank.entity.Account;
import pl.bank.entity.Customer;
import pl.bank.entity.Transaction;
import pl.bank.entity.Transfer;
import pl.bank.service.AccountsService;
import pl.bank.service.CustomersService;
import pl.bank.service.TransactionsService;
import pl.bank.service.TransfersService;

import java.util.List;

@RestController
@EnableTransactionManagement
@RequestMapping("/api")
public class BankController {


    private CustomersService customersService;
    private AccountsService accountsService;
    private TransactionsService transactionsService;
    private TransfersService transfersService;

    @Autowired
    public BankController(CustomersService customersService, AccountsService accountsService,
                          TransactionsService transactionsService, TransfersService transfersService) {
        this.customersService = customersService;
        this.accountsService = accountsService;
        this.transactionsService = transactionsService;
        this.transfersService = transfersService;
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

    @GetMapping("/account")
    public List<Account> getAccounts() {
        return accountsService.getAccounts();
    }

    @GetMapping("/account/{accountId}")
    public Account getAccount(@PathVariable int accountId) {
        return accountsService.getAccount(accountId);
    }

    @DeleteMapping("/account/{accountId}")
    public String deleteAccount(@PathVariable int accountId) {
        return accountsService.deleteAccount(accountId);
    }

    @PutMapping("/account")
    public Account updateAccount(@RequestBody Account account) {
        return accountsService.updateAccount(account);
    }

    @PostMapping("/account")
    public Account createAccount(@RequestBody Account account) {
        return accountsService.createAccount(account);
    }

    @GetMapping("/transaction")
    public List<Transaction> getTransactions() {
        return transactionsService.getTransactions();
    }

    @GetMapping("/transaction/{transactionId}")
    public Transaction getTransaction(@PathVariable int transactionId) {
        return transactionsService.getTransaction(transactionId);
    }

    @DeleteMapping("/transaction/{transactionId}")
    public String deleteTransaction(@PathVariable int transactionId) {
        return transactionsService.deleteTransaction(transactionId);
    }

    @PutMapping("/transaction")
    public Transaction updateTransaction(@RequestBody Transaction transaction) {
        return transactionsService.updateTransaction(transaction);
    }

    @PostMapping("/transaction")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionsService.createTransaction(transaction);
    }

    @GetMapping("/transfer")
    public List<Transfer> getTransfers() {
        return transfersService.getTransfers();
    }

    @GetMapping("/transfer/{transferId}")
    public Transfer getTransfer(@PathVariable int transferId) {
        return transfersService.getTransfer(transferId);
    }

    @DeleteMapping("/transfer/{transferId}")
    public String deleteTransfer(@PathVariable int transferId) {
        return transfersService.deleteTransfer(transferId);
    }

    @PutMapping("/transfer")
    public Transfer updateTransfer(@RequestBody Transfer transfer) {
        return transfersService.updateTransfer(transfer);
    }

    @PostMapping("/transfer")
    public Transfer createTransfer(@RequestBody Transfer transfer) {
        return transfersService.createTransfer(transfer);
    }

}
