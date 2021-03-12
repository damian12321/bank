package pl.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import pl.bank.entity.Transaction;
import pl.bank.service.TransactionsService;

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

}
