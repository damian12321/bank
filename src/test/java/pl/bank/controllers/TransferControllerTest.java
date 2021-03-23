package pl.bank.controllers;

import org.junit.jupiter.api.MethodOrderer;
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
import pl.bank.entity.Account;
import pl.bank.exception.CustomException;
import pl.bank.exception.NoAccessException;
import pl.bank.service.AccountsService;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DemoAppConfig.class, DemoSecurityConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransferControllerTest {
    static AccountsService accountsService;
    static int accountNumber1 = 4;
    static int accountNumber2 = 5;

    @Autowired
    public void setAccountsService(AccountsService accountsService) {
        TransferControllerTest.accountsService = accountsService;
    }

    @Test
    void transferMoney() {
        Account account1 = new Account(1, accountNumber1, 222, 300.00f);
        Account account2 = new Account(2, accountNumber2, 222, 300.00f);
        Account response1 = accountsService.createAccount(account1);
        Account response2 = accountsService.createAccount(account2);
        String answer = accountsService.transferMoney(account1.getAccountNumber(), account1.getPinNumber(), account2.getAccountNumber(), account1.getBalance(), "");
        String expected = "The money has been transferred from " + account1.getAccountNumber() + " to " + account2.getAccountNumber() + ".";
        assertEquals(expected, answer);
        assertThrows(CustomException.class, () ->
                accountsService.transferMoney(account1.getAccountNumber(), account1.getPinNumber(), account2.getAccountNumber(), account1.getBalance() + 200, ""));
        assertThrows(NoAccessException.class, () ->
                accountsService.transferMoney(account1.getAccountNumber(), account1.getPinNumber() + 1, account2.getAccountNumber(), account1.getBalance(), ""));
        assertThrows(NoAccessException.class, () ->
                accountsService.transferMoney(account1.getAccountNumber(), account1.getPinNumber() + 1, account2.getAccountNumber(), account1.getBalance(), ""));
        assertThrows(NoAccessException.class, () ->
                accountsService.transferMoney(account1.getAccountNumber(), account1.getPinNumber() + 1, account2.getAccountNumber(), account1.getBalance(), ""));
        assertThrows(CustomException.class, () ->
                accountsService.transferMoney(account1.getAccountNumber(), account1.getPinNumber(), account2.getAccountNumber(), account1.getBalance(), ""));
        accountsService.deleteAccount(response1.getId());
        accountsService.deleteAccount(response2.getId());
    }

    @Test
    void depositMoney() {
        Account account1 = new Account(1, accountNumber1, 222, 300.00f);
        Account response = accountsService.createAccount(account1);
        String answer = accountsService.depositMoney(account1.getAccountNumber(), account1.getPinNumber(), account1.getBalance());
        String expected = "Deposit has been completed.";
        assertEquals(expected, answer);
        assertThrows(NoAccessException.class, () ->
                accountsService.depositMoney(account1.getAccountNumber(), account1.getPinNumber() + 1, account1.getBalance()));
        assertThrows(NoAccessException.class, () ->
                accountsService.depositMoney(account1.getAccountNumber(), account1.getPinNumber() + 1, account1.getBalance()));
        assertThrows(NoAccessException.class, () ->
                accountsService.depositMoney(account1.getAccountNumber(), account1.getPinNumber() + 1, account1.getBalance()));
        assertThrows(CustomException.class, () ->
                accountsService.depositMoney(account1.getAccountNumber(), account1.getPinNumber(), account1.getBalance()));
        accountsService.deleteAccount(response.getId());
    }

    @Test
    void withdrawMoney() {
        Account account1 = new Account(1, accountNumber1, 222, 300.00f);
        Account response = accountsService.createAccount(account1);
        String answer = accountsService.withdrawMoney(account1.getAccountNumber(), account1.getPinNumber(), account1.getBalance());
        String expected = "Withdrawal has been completed.";
        assertEquals(expected, answer);
        assertThrows(CustomException.class, () ->
                accountsService.withdrawMoney(account1.getAccountNumber(), account1.getPinNumber(), account1.getBalance() + 200));
        assertThrows(NoAccessException.class, () ->
                accountsService.withdrawMoney(account1.getAccountNumber(), account1.getPinNumber() + 1, account1.getBalance()));
        assertThrows(NoAccessException.class, () ->
                accountsService.withdrawMoney(account1.getAccountNumber(), account1.getPinNumber() + 1, account1.getBalance()));
        assertThrows(NoAccessException.class, () ->
                accountsService.withdrawMoney(account1.getAccountNumber(), account1.getPinNumber() + 1, account1.getBalance()));
        assertThrows(CustomException.class, () ->
                accountsService.withdrawMoney(account1.getAccountNumber(), account1.getPinNumber(), account1.getBalance()));
        accountsService.deleteAccount(response.getId());
    }

}