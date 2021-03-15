package pl.bank.controllers;


import com.google.gson.Gson;

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
import java.io.BufferedReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import pl.bank.entity.Customer;
import pl.bank.service.AccountsService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DemoAppConfig.class, DemoSecurityConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountsControllerTest {
    @Autowired
    AccountsService accountsService;
    static List<Account> list;
    static int idNumber=0;

    @BeforeAll
    public static void init() throws IOException {
        URL url = new URL("http://localhost:8080/bank_war/api/account/");
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection con = (HttpURLConnection) urlConnection;
        con.setRequestMethod("GET");
        String userPass = "admin" + ":" + "admin";
        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userPass.getBytes());
        con.setRequestProperty("Authorization", basicAuth);
        InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
        Type listType = new TypeToken<ArrayList<Account>>() {
        }.getType();
        list = new Gson().fromJson(inputStreamReader, listType);
    }

    @Test
    @Order(1)
    void createAccount() throws IOException {
        URL url = new URL("http://localhost:8080/bank_war/api/account/");
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection con = (HttpURLConnection) urlConnection;
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String userPass = "admin" + ":" + "admin";
        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userPass.getBytes());
        con.setRequestProperty("Authorization", basicAuth);
        Account account = new Account(1, 22, 222, 300.00f, new Customer("Damian", "Juruś"), 3, true);
        String object = new Gson().toJson(account);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = object.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        Account result = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            result = new Gson().fromJson(response.toString(), Account.class);
            System.out.println(response.toString());

        }
        idNumber=result.getId();
        list.add(account);
        assertEquals(account, result);


    }

    @Test
    @Order(2)
    void updateAccount() throws IOException {
        URL url = new URL("http://localhost:8080/bank_war/api/account/");
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection con = (HttpURLConnection) urlConnection;
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestMethod("PUT");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String userPass = "admin" + ":" + "admin";
        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userPass.getBytes());
        con.setRequestProperty("Authorization", basicAuth);
        Account account = new Account(idNumber, 22, 223, 300.00f, new Customer("Damian", "Juruś"), 3, true);
        String object = new Gson().toJson(account);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = object.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        Account result = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            result = new Gson().fromJson(response.toString(), Account.class);
            System.out.println(response.toString());

        }
        assertEquals(account, result);
    }


    @Test
    @Order(3)
    void getAccount() throws IOException {
        URL url = new URL("http://localhost:8080/bank_war/api/account/22/223");
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection con = (HttpURLConnection) urlConnection;
        con.setRequestMethod("GET");
        String userPass = "admin" + ":" + "admin";
        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userPass.getBytes());
        con.setRequestProperty("Authorization", basicAuth);
        InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
        Account account = new Gson().fromJson(inputStreamReader, Account.class);
        System.out.println(account);
        inputStreamReader.close();
    }

    @Test
    @Order(4)
    void getAccounts() throws IOException {
        URL url = new URL("http://localhost:8080/bank_war/api/account/");
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection con = (HttpURLConnection) urlConnection;
        con.setRequestMethod("GET");
        String userPass = "admin" + ":" + "admin";
        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userPass.getBytes());
        con.setRequestProperty("Authorization", basicAuth);
        InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
        Type listType = new TypeToken<ArrayList<Account>>() {
        }.getType();
        List<Account> list1 = new Gson().fromJson(inputStreamReader, listType);
        assertEquals(list,list1);
    }

    @Test
    @Order(5)
    void deleteAccount() throws IOException {
        URL url = new URL("http://localhost:8080/bank_war/api/account/22");
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection con = (HttpURLConnection) urlConnection;
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String userPass = "admin" + ":" + "admin";
        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userPass.getBytes());
        con.setRequestProperty("Authorization", basicAuth);
        StringBuilder response = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

        }
        System.out.println(response.toString());
    }


}