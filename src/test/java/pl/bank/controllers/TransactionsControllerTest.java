package pl.bank.controllers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.bank.configuration.DemoAppConfig;
import pl.bank.configuration.DemoSecurityConfig;
import pl.bank.entity.Transaction;
import pl.bank.enums.TransactionType;
import pl.bank.exception.CustomException;
import pl.bank.service.TransactionsService;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DemoAppConfig.class, DemoSecurityConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionsControllerTest {
    static TransactionsService transactionsService;
    static List<Transaction> list;
    static int transactionNumber = 1;

    @Autowired
    public void setTransactionsService(TransactionsService transactionsService) {
        TransactionsControllerTest.transactionsService = transactionsService;
        list = transactionsService.getTransactions();
    }

    @Test
    @Order(1)
    void createTransaction() {
        Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, 100, new Date(), "Description");
        Transaction answer = transactionsService.createTransaction(transaction);
        transactionNumber = answer.getId();
        assertEquals(transaction, answer);
    }

    @Test
    @Order(2)
    void updateTransaction() {
        Transaction transaction = new Transaction(transactionNumber, TransactionType.WITHDRAWAL, 100, new Date(), "Description");
        Transaction answer = transactionsService.updateTransaction(transaction);
        assertEquals(transaction, answer);
    }


    @Test
    @Order(3)
    void getTransaction() {
        Transaction transaction = new Transaction(transactionNumber, TransactionType.WITHDRAWAL, 100, new Date(), "Description");
        Transaction answer = transactionsService.getTransaction(transactionNumber);
        assertEquals(transaction, answer);
    }

    @Test
    @Order(4)
    void deleteTransaction() {
        String answer = transactionsService.deleteTransaction(transactionNumber);
        String expected = "Transaction with id: " + transactionNumber + " has been deleted";
        assertEquals(expected, answer);
        assertThrows(CustomException.class, () -> transactionsService.deleteTransaction(transactionNumber));
    }

    @Test
    @Order(5)
    void getTransactions() {
        List<Transaction> answer = transactionsService.getTransactions();
        assertEquals(list, answer);
    }
}