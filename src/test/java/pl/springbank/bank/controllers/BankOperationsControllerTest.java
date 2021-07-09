package pl.springbank.bank.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
    public void shouldReturnIsOkForTransferWithCorrectPinNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/transfer/200/1234/201/100/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"TEXT\"")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnIsUnauthorizedForTransferWithIncorrectPinNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/transfer/200/1233/201/100/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"TEXT\"")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturnIsBadRequestForTransferWithIncorrectAmountValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/transfer/200/1234/201/100000/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"TEXT\"")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnIsNotFoundForTransferWithInvalidSenderAccountNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/transfer/200/1234/199/100/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"TEXT\"")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnIsNotFoundForTransferWithInvalidDestinationAccountNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/transfer/199/1234/201/100/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"TEXT\"")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnIsUnauthorizedForWithdrawWithIncorrectPinNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/withdraw/200/1233/100")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturnIsBadRequestForWithdrawWithIncorrectAmountValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/withdraw/200/1234/1000000")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnIsOkForWithdrawWithCorrectValues() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/withdraw/200/1234/100")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnIsUnauthorizedForDepositWithIncorrectPinNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/deposit/200/1233/100")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturnIsBadRequestForDepositWithIncorrectAmountValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/deposit/200/1234/-200")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnIsOkForDepositWithCorrectValues() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/deposit/200/1234/100")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
