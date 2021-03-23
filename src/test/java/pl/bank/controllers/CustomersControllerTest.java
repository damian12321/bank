package pl.bank.controllers;

import org.junit.After;
import org.junit.AfterClass;
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
import pl.bank.entity.Customer;
import pl.bank.exception.NoAccessException;
import pl.bank.service.AccountsService;
import pl.bank.service.CustomersService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DemoAppConfig.class, DemoSecurityConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomersControllerTest {
    static CustomersService customersService;
    static AccountsService accountsService;
    static List<Customer> list;
    static int number;
    static Account account1;

    @Autowired
    public void setCustomersService(CustomersService customersService,AccountsService accountsService) {
        CustomersControllerTest.customersService = customersService;
        CustomersControllerTest.accountsService=accountsService;
        list = customersService.getCustomers();
    }
    @Test
    @Order(1)
    void createCustomer() {
        account1=new Account( 1, 222, 300.00f);
        Customer customer = new Customer(1, "Damian", "Juruś",account1,"1234");
        Customer answer = customersService.createCustomer(customer);
        CustomersControllerTest.number=answer.getId();
        CustomersControllerTest.account1=answer.getAccount();
        customer.setId(answer.getId());
        assertEquals(customer, answer);
    }

    @Test
    @Order(2)
    void updateCustomer() {
        Customer customer = new Customer(number, "Damian", "Juruś",account1,"12345");
        Customer answer = customersService.updateCustomer(customer);
        assertEquals(customer, answer);
    }


    @Test
    @Order(3)
    void getCustomer() {
        Customer customer = new Customer(number, "Damian", "Juruś",account1,"12345");
        Customer answer = customersService.getCustomer(number,"12345");
        assertEquals(customer, answer);
        assertThrows(NoAccessException.class,()->customersService.getCustomer(number,"1234"));
    }

    @Test
    @Order(4)
    void deleteCustomer() {
        String answer = customersService.deleteCustomer(number);
        String expected = "Customer with id: " + number + " has been deleted.";
        assertEquals(expected, answer);
    }

    @Test
    @Order(5)
    void getCustomers() {
        List<Customer> answer = customersService.getCustomers();
        assertEquals(list, answer);
    }
    @AfterAll
    public static void reset()
    {
        List<Account> list = accountsService.getAccounts();
        if (list.contains(account1))
            accountsService.deleteAccount(account1.getId());
    }
}