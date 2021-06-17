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
        account1AsJson = "{\"id\":3,\"firstName\":\"Test1\",\"lastName\":\"Test1\",\"accountNumber\":202,\"balance\":1000.0," +
                "\"loginAttempts\":3,\"isActive\":true,\"password\":\"1234\",\"pinNumber\":1234}";
        account2AsJson = "{\"id\":4,\"firstName\":\"Test2\",\"lastName\":\"Test2\",\"accountNumber\":203,\"balance\":1000.0," +
                "\"loginAttempts\":3,\"isActive\":true,\"password\":\"1234\",\"pinNumber\":1234}";
        account2AsJsonWithUpdate = "{\"id\":4,\"firstName\":\"Test2\",\"lastName\":\"Test2\",\"accountNumber\":203,\"balance\":2000.0," +
                "\"loginAttempts\":3,\"isActive\":true,\"password\":\"2000\",\"pinNumber\":2000}";
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    @Order(1)
    public void postNewAccount() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(account1AsJson)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    @Order(2)
    public void putNewAccount() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(account2AsJson)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    @Order(3)
    public void updateAccount() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(account2AsJsonWithUpdate)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    @Order(4)
    public void deleteAsCustomer() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/accounts/3")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @Order(5)
    public void deleteAsAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/accounts/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
