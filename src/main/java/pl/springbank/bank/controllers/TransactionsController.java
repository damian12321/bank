package pl.springbank.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import pl.springbank.bank.entity.Transaction;
import pl.springbank.bank.service.TransactionsService;

import java.util.List;

@RestController
@EnableTransactionManagement
@RequestMapping("/api")
public class TransactionsController {


    private TransactionsService transactionsService;

    @Autowired
    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return transactionsService.getTransactions();
    }

    @GetMapping("/transactions/{transactionId}")
    public Transaction getTransaction(@PathVariable int transactionId) {
        return transactionsService.getTransaction(transactionId);
    }

    @DeleteMapping("/transactions/{transactionId}")
    public String deleteTransaction(@PathVariable int transactionId) {
        return transactionsService.deleteTransaction(transactionId);
    }

    @PutMapping("/transactions")
    public Transaction updateTransaction(@RequestBody Transaction transaction) {
        return transactionsService.updateTransaction(transaction);
    }

}
