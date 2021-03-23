package pl.bank.controllers;

import org.junit.jupiter.api.*;
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

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DemoAppConfig.class, DemoSecurityConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountsControllerTest {

    static AccountsService accountsService;
    static List<Account> list;
    static int idNumber = 0;
    static int accountNumber = 1;

    @Autowired
    public void setAccountsService(AccountsService accountsService) {
        AccountsControllerTest.accountsService = accountsService;
        list = accountsService.getAccounts();
    }

    @Test
    @Order(1)
    void getFreeAccountNumber() {
        int highestNumber = 1;
        for (Account account : list) {
            if (account.getAccountNumber() > highestNumber) {
                highestNumber = account.getAccountNumber();
            }
        }
        int answer = accountsService.getFreeAccountNumber();
        assertEquals(++highestNumber, answer);
    }

    @Test
    @Order(2)
    void createAccount() {
        Account account = new Account(1, 1, 222, 300.00f);
        Account answer = accountsService.createAccount(account);
        idNumber = answer.getId();
        assertEquals(account, answer);
    }

    @Test
    @Order(3)
    void updateAccount() {
        Account account = new Account(idNumber, accountNumber, 223, 300.00f);
        Account answer = accountsService.updateAccount(account);
        assertEquals(account, answer);
    }


    @Test
    @Order(4)
    void getAccount() {
        Account account = new Account(idNumber, accountNumber, 223, 300.00f);
        Account answer = accountsService.getAccount(accountNumber, 223);
        assertEquals(account, answer);
        assertThrows(NoAccessException.class, () -> accountsService.getAccount(accountNumber, 224));
        assertThrows(NoAccessException.class, () -> accountsService.getAccount(accountNumber, 224));
        assertThrows(NoAccessException.class, () -> accountsService.getAccount(accountNumber, 224));
        assertThrows(CustomException.class, () -> accountsService.getAccount(accountNumber, 224));
    }

    @Test
    @Order(5)
    void deleteAccount() {
        String answer = accountsService.deleteAccount(idNumber);
        String expected = "Account with id: " + idNumber + " has been deleted.";
        assertEquals(expected, answer);
        assertThrows(CustomException.class, () -> accountsService.deleteAccount(idNumber));
    }

    @Test
    @Order(6)
    void getAccounts() {
        List<Account> answer = accountsService.getAccounts();
        assertEquals(list, answer);
    }

    @AfterAll //cleaning accounts
    public static void reset() {
        List<Account> list = accountsService.getAccounts();
        Account account = new Account(idNumber, accountNumber, 223, 300.00f);
        if (list.contains(account))
            accountsService.deleteAccount(account.getAccountNumber());

    }


}