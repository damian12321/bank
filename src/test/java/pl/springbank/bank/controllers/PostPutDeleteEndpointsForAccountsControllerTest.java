package pl.springbank.bank.controllers;


import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostPutDeleteEndpointsForAccountsControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    AccountsService accountsService;
    private static String account1AsJson;
    private static String account2AsJson;
    private static String account2AsJsonWithUpdate;

    @BeforeAll
    public static void init() {
        account1AsJson = "{\"id\":0,\"firstName\":\"Test1\",\"lastName\":\"Test1\",\"accountNumber\":202,\"balance\":1000.0," +
                "\"loginAttempts\":3,\"isActive\":true,\"password\":\"1234\",\"pinNumber\":1234,\"email\":\"email1@gmail.com\"}";
        account2AsJson = "{\"id\":0,\"firstName\":\"Test2\",\"lastName\":\"Test2\",\"accountNumber\":203,\"balance\":1000.0," +
                "\"loginAttempts\":3,\"isActive\":true,\"password\":\"1234\",\"pinNumber\":1234,\"email\":\"email2@gmail.com\"}";
        account2AsJsonWithUpdate = "{\"id\":4,\"firstName\":\"Test2\",\"lastName\":\"Test2\",\"accountNumber\":203,\"balance\":2000.0," +
                "\"loginAttempts\":3,\"isActive\":true,\"password\":\"2000\",\"pinNumber\":2000,\"email\":\"email3@gmail.com\"}";
    }

    @Test
    @Order(1)
    public void shouldReturnIsCreatedForPostAccountWithCorrectData() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(account1AsJson)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void shouldReturnIsCreatedForPutAccountWithCorrectData() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(account2AsJson)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    public void shouldReturnIsOkForUpdateAccountWithCorrectData() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(account2AsJsonWithUpdate)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void shouldReturnIsOkForChangePasswordWithValidData() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/accounts/changePassword/3/1234/4444")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Order(5)
    @RepeatedTest(3)
    public void shouldReturnIsUnauthorizedForChangePasswordWithInvalidData() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/accounts/changePassword/3/1234/4444")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
    @Test
    @Order(6)
    public void shouldReturnIsOkForChangePinWithValidData() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/accounts/changePin/203/1234/4444")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Order(7)
    @RepeatedTest(3)
    public void shouldReturnIsUnauthorizedForChangePinWithInvalidData() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/accounts/changePin/203/1234/4444")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
    @Test
    @Order(8)
    public void shouldReturnIsLockedAfterPreviousFailAttemptsForPasswordChange() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/accounts/changePassword/3/1234/4444")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isLocked());
    }
    @Test
    @Order(9)
    public void shouldReturnIsLockedAfterPreviousFailAttemptsForPinChange() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/accounts/changePin/203/1234/4444")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isLocked());
    }
    @Test
    @Order(10)
    public void shouldReturnIsUnauthorizedForDeleteAccountAsNotAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/accounts/3")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @Order(11)
    public void shouldReturnIsOkForDeleteAccountAsAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/accounts/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
