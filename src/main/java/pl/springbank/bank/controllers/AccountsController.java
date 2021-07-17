package pl.springbank.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import pl.springbank.bank.entity.Account;
import pl.springbank.bank.entity.Transaction;
import pl.springbank.bank.service.AccountsService;

import javax.validation.Valid;
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

    @GetMapping("/accounts/{accountId}/{password}")
    public Account getAccount(@PathVariable int accountId, @PathVariable String password) {
        return accountsService.getAccount(accountId, password);
    }

    @DeleteMapping("/accounts/{accountId}")
    public String deleteAccount(@PathVariable int accountId) {
        return accountsService.deleteAccount(accountId);
    }

    @PutMapping("/accounts")
    public ResponseEntity<Account> saveOrUpdateAccount(@Valid @RequestBody Account account) {
        long accountId = account.getId();
        Account tempAccount = accountsService.saveOrUpdateAccount(account);
        if (tempAccount.getId() == accountId)
            return new ResponseEntity<>(tempAccount, HttpStatus.OK);
        return new ResponseEntity<>(tempAccount, HttpStatus.CREATED);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
        account = accountsService.createAccount(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/accounts/{accountId}/{password}/transactions")
    public List<Transaction> getAccountsTransactions(@PathVariable int accountId, @PathVariable String password) {
        return accountsService.getAccountsTransactions(accountId, password);
    }

    @PostMapping("accounts/changePassword/{id}/{oldPassword}/{newPassword}")
    public String changePassword(@PathVariable int id,@PathVariable String oldPassword,@PathVariable String newPassword)
    {
        return accountsService.changePassword(id,oldPassword,newPassword);
    }

    @PostMapping("accounts/changePin/{accountNumber}/{oldPin}/{newPin}")
    public String changePinNumber(@PathVariable int accountNumber,@PathVariable int oldPin,@PathVariable int newPin)
    {
        return accountsService.changePinNumber(accountNumber,oldPin,newPin);
    }

}
