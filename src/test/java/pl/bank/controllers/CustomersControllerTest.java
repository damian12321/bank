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
import pl.bank.entity.Customer;
import pl.bank.service.CustomersService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DemoAppConfig.class, DemoSecurityConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomersControllerTest {
    static CustomersService customersService;
    static int idNumber = 0;
    static List<Customer> list;

    @Autowired
    public void setCustomersService(CustomersService customersService) {
        CustomersControllerTest.customersService = customersService;
        list = customersService.getCustomers();
    }

    @Test
    @Order(1)
    void createCustomer() {
        Customer customer = new Customer(1, "Damian", "Juruś");
        Customer answer = customersService.createCustomer(customer);
        idNumber = answer.getId();
        assertEquals(customer, answer);
    }

    @Test
    @Order(2)
    void updateCustomer() {
        Customer customer = new Customer(idNumber, "Damian", "Jurus");
        Customer answer = customersService.updateCustomer(customer);
        assertEquals(customer, answer);
    }


    @Test
    @Order(3)
    void getCustomer() {
        Customer customer = new Customer(idNumber, "Damian", "Jurus");
        Customer answer = customersService.getCustomer(idNumber);
        assertEquals(customer, answer);
    }

    @Test
    @Order(4)
    void deleteCustomer() {
        String answer = customersService.deleteCustomer(idNumber);
        String expected = "Customer with id: " + idNumber + " has been deleted";
        assertEquals(expected, answer);
    }

    @Test
    @Order(5)
    void getCustomers() {
        List<Customer> answer = customersService.getCustomers();
        assertEquals(list, answer);
    }
}