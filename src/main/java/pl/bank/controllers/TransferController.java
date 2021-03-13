package pl.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import pl.bank.service.AccountsService;

@RestController
@EnableTransactionManagement
@RequestMapping("/api")
public class TransferController {
    private AccountsService accountsService;

    @Autowired
    public TransferController(AccountsService accountsService) {
        this.accountsService = accountsService;

    }

    @PostMapping("/transfer/{fromAccountNumber}/{pinNumber}/{toAccountNumber}/{amount}")
    public String transferMoney(@PathVariable int fromAccountNumber, @PathVariable int pinNumber, @PathVariable int toAccountNumber, @PathVariable float amount) {
        return accountsService.transferMoney(fromAccountNumber, pinNumber, toAccountNumber, amount);
    }

}
