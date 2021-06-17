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
class GetEndpointsForAccountsControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    AccountsService accountsService;

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void getAccountsAsCustomer() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/accounts")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getAccountsAsAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/accounts")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void getSpecificAccountWithCorrectPassword() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/accounts/1/1234")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void getSpecificAccountWithIncorrectPassword() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/accounts/1/1235")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getFreeAccountNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/accounts/number")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void getAccountTransactionsWithCorrectPassword() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/accounts/1/1234/transactions")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void getAccountTransactionsWithIncorrectPassword() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/accounts/1/1233/transactions")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


}
