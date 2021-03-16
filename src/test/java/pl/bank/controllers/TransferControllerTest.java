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
import pl.bank.exception.PinNumberException;
import pl.bank.service.AccountsService;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DemoAppConfig.class, DemoSecurityConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransferControllerTest {
    static AccountsService accountsService;
    static int accountNumber1 = 1;
    static int accountNumber2 = 2;

    @Autowired
    public void setAccountsService(AccountsService accountsService) {
        TransferControllerTest.accountsService = accountsService;
    }

    @Test
    void transferMoney() {
        Account account1 = new Account(1, accountNumber1, 222, 300.00f);
        Account account2 = new Account(1, accountNumber2, 222, 300.00f);
        accountsService.createAccount(account1);
        accountsService.createAccount(account2);
        String answer = accountsService.transferMoney(account1.getAccountNumber(), account1.getPinNumber(), account2.getAccountNumber(), account1.getBalance());
        String expected = "The money has been transferred from " + account1.getAccountNumber() + " to " + account2.getAccountNumber() + ".";
        assertEquals(expected, answer);
        assertThrows(PinNumberException.class, () ->
                accountsService.transferMoney(account1.getAccountNumber(), account1.getPinNumber() + 1, account2.getAccountNumber(), account1.getBalance()));
        assertThrows(PinNumberException.class, () ->
                accountsService.transferMoney(account1.getAccountNumber(), account1.getPinNumber() + 1, account2.getAccountNumber(), account1.getBalance()));
        assertThrows(PinNumberException.class, () ->
                accountsService.transferMoney(account1.getAccountNumber(), account1.getPinNumber() + 1, account2.getAccountNumber(), account1.getBalance()));
        assertThrows(CustomException.class, () ->
                accountsService.transferMoney(account1.getAccountNumber(), account1.getPinNumber() + 1, account2.getAccountNumber(), account1.getBalance()));
        accountsService.deleteAccount(accountNumber1);
        accountsService.deleteAccount(accountNumber2);
    }
}