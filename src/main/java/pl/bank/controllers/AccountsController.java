package pl.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import pl.bank.entity.Account;
import pl.bank.service.AccountsService;

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


}
