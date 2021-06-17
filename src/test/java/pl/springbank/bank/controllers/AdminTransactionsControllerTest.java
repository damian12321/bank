package pl.springbank.bank.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.springbank.bank.service.AccountsService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminTransactionsControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    AccountsService accountsService;
    private String transaction1AsJson = "{\"id\":2,\"transactionType\":\"INCOMING_TRANSFER\",\"amount\":500.0,\"date\":\"16-06-2021 10:00:00\"," +
            "\"description\":\"NEW DESCRIPTION\"}";

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void getTransactionsAsCustomer() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/transactions")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getTransactionsAsAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/transactions")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void getSpecificTransactionAsCustomer() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/transactions/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getSpecificTransactionAsAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/transactions/2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void deleteTransactionAsCustomer() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/transactions/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void deleteTransactionAsAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/transactions/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void updateTransaction() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put("/api/transactions/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transaction1AsJson)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
