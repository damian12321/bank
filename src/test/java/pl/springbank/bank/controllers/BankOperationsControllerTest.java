package pl.springbank.bank.controllers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
public class BankOperationsControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    AccountsService accountsService;

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void makeTransferWithCorrectPin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/transfer/200/1234/201/100/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"TEXT\"")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void makeTransferWithIncorrectPin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/transfer/200/1233/201/100/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"TEXT\"")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void makeTransferWithIncorrectAmountValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/transfer/200/1234/201/100000/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"TEXT\"")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void makeTransferWithIncorrectDestinationAccountNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/transfer/200/1234/199/100/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"TEXT\"")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void makeTransferWithIncorrectSenderAccountNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/transfer/199/1234/201/100/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"TEXT\"")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void makeWithdrawWithIncorrectPin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/withdraw/200/1233/100")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void makeWithdrawWithIncorrectValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/withdraw/200/1234/1000000")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void makeWithdrawWithCorrectValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/withdraw/200/1234/100")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void makeDepositWithIncorrectPin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/deposit/200/1233/100")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void makeDepositWithIncorrectValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/deposit/200/1234/-200")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void makeDepositWithCorrectValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/deposit/200/1234/100")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
