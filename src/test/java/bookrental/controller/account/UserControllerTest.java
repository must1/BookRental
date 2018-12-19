package bookrental.controller.account;

import bookrental.model.account.User;
import bookrental.service.account.UserService;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void createUser() throws Exception {
        User newUser = createDummyUser();

        when(userService.createUser(newUser)).thenReturn(newUser);

        String expected = "{\"id\":0,\"name\":\"must\",\"password\":\"123\"}";

        MvcResult mvcResult = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String actual = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, actual);

        verify(userService, times(1)).createUser(newUser);
    }

    @Test
    public void deleteAccount() throws Exception {
        User newUser = createDummyUser();

        when(userService.deleteAccount(newUser.getId())).thenReturn(newUser);

        String expected = "{\"id\":0,\"name\":\"must\",\"password\":\"123\"}";

        MvcResult mvcResult = mockMvc.perform(delete("/user")
                .param("id", String.valueOf(0)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String actual = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, actual);

        verify(userService, times(1)).deleteAccount(newUser.getId());
    }

    private User createDummyUser() {
        return new User(0, "must", "123");
    }
}