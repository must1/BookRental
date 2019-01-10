package bookrental.account;

import bookrental.controllers.AccountController;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void createUser() throws Exception {
        Account newUser = createDummyAccount();

        when(accountService.createAccount(newUser)).thenReturn(newUser);

        String expected = "{\"id\":0,\"name\":\"must\",\"password\":\"123\",\"amountOfCashToPay\":0}";

        MvcResult mvcResult = mockMvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String actual = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, actual);

        verify(accountService, times(1)).createAccount(newUser);
    }

    @Test
    public void deleteAccount() throws Exception {
        Account newUser = createDummyAccount();

        when(accountService.deleteAccount(newUser.getId())).thenReturn(newUser);

        String expected = "{\"id\":0,\"name\":\"must\",\"password\":\"123\",\"amountOfCashToPay\":0}";

        MvcResult mvcResult = mockMvc.perform(delete("/account")
                .param("id", String.valueOf(0)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String actual = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, actual);

        verify(accountService, times(1)).deleteAccount(newUser.getId());
    }

    private Account createDummyAccount() {
        return new Account(0, "must", "123",0);
    }
}