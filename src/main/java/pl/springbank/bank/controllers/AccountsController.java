package pl.springbank.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import pl.springbank.bank.entity.Account;
import pl.springbank.bank.service.AccountsService;


import java.util.List;

@RestController
@EnableTransactionManagement
@RequestMapping("/api")
public class AccountsController {


    private AccountsService accountsService;


    @Autowired
    public AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }


    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountsService.getAccounts();
    }

    @GetMapping("/accounts/{accountId}/{pinNumber}")
    public Account getAccount(@PathVariable int accountId, @PathVariable int pinNumber) {
        return accountsService.getAccount(accountId, pinNumber);
    }

    @DeleteMapping("/accounts/{accountId}")
    public String deleteAccount(@PathVariable int accountId) {
        return accountsService.deleteAccount(accountId);
    }

    @PutMapping("/accounts")
    public Account updateAccount(@RequestBody Account account) {
        return accountsService.updateAccount(account);
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account) {
        return accountsService.createAccount(account);
    }

    @GetMapping("/accounts/number")
    public int getFreeAccountNumber() {
        return accountsService.getFreeAccountNumber();
    }


}
