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
    public void shouldReturnIsUnauthorizedForGetAccountsAsNotAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/accounts")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldReturnIsOkForGetAccountsAsAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/accounts")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnIsOkForGetAccountWithCorrectPinNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/accounts/1/1234")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnIsUnauthorizedForGetAccountWithIncorrectPinNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/accounts/1/1235")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturnIsOkForGetTransactionsWithCorrectPassword() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/accounts/2/1234/transactions")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnIsUnauthorizedForGetTransactionsWithIncorrectPassword() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/accounts/2/1233/transactions")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


}
