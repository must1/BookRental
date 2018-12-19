package bookrental.controller.account;

import bookrental.model.account.User;
import bookrental.model.book.Book;
import bookrental.model.book.BookRentals;
import bookrental.service.account.UserRentalsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRentalsController.class)
public class UserRentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRentalsService userRentalsService;

    @Test
    public void findUserRentalsByGivenID() throws Exception {
        List<BookRentals> books = new ArrayList<>();
        User user = createDummyUser();
        Book book = createDummyBook();
        BookRentals bookToRent = new BookRentals(book, user);
        books.add(bookToRent);

        when(userRentalsService.findUserRentalsByGivenID(bookToRent.getId())).thenReturn(books);

        String expected = "[{\"id\":0,\"book\":{\"id\":0,\"title\":\"W pustyni i w puszczy\",\"author\":\"Henryk Sienkiewicz\",\"category\":\"dramat\",\"available\":true},\"user\":{\"id\":0,\"name\":\"must\",\"password\":\"123\"}}]";

        MvcResult mvcResult = mockMvc.perform(get("/books/rentals/0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, content);

        verify(userRentalsService, times(1)).findUserRentalsByGivenID(user.getId());
    }

    private User createDummyUser() {
        return new User("must", "123");
    }

    private Book createDummyBook() {
        return new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", true);
    }

}