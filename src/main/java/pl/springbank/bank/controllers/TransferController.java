package pl.springbank.bank.controllers;

import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import pl.springbank.bank.service.AccountsService;

@RestController
@EnableTransactionManagement
@RequestMapping("/api")
public class TransferController {

    private AccountsService accountsService;

    @Autowired
    public TransferController(AccountsService accountsService) {
        this.accountsService = accountsService;

    }

    @PostMapping("/transfer/{fromAccountNumber}/{pinNumber}/{toAccountNumber}/{amount}/")
    public String transferMoney(@PathVariable int fromAccountNumber, @PathVariable int pinNumber, @PathVariable int toAccountNumber,
                                @PathVariable float amount, @RequestBody TextNode description) {
        return accountsService.transferMoney(fromAccountNumber, pinNumber, toAccountNumber, amount, description.asText());
    }

    @PostMapping("/deposit/{accountNumber}/{pinNumber}/{amount}")
    public String depositMoney(@PathVariable int accountNumber, @PathVariable int pinNumber,
                               @PathVariable float amount) {
        return accountsService.depositMoney(accountNumber, pinNumber, amount);
    }

    @PostMapping("/withdraw/{accountNumber}/{pinNumber}/{amount}")
    public String withdrawMoney(@PathVariable int accountNumber, @PathVariable int pinNumber, @PathVariable float amount) {
        return accountsService.withdrawMoney(accountNumber, pinNumber, amount);
    }

}
