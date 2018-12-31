package bookrental.controller.book;

import bookrental.book.Book;
import bookrental.controllers.BookCrudOperationsController;
import bookrental.book.BookCrudOperationsService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookCrudOperationsController.class)
public class BookCrudOperationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookCrudOperationsService bookCrudOperationsService;

    @Test
    public void createBookWithValidUserData() throws Exception {
        Book newBook = new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", true);

        when(bookCrudOperationsService.createBook(any())).thenReturn(newBook);

        String expected = "{\"id\":0,\"title\":\"W pustyni i w puszczy\",\"author\":\"Henryk Sienkiewicz\",\"category\":\"dramat\",\"available\":true}";

        MvcResult mvcResult = mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(newBook)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, content);

        verify(bookCrudOperationsService, times(1)).createBook(any());
    }

    @Test
    public void getAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        Book book = createDummyBook();
        books.add(book);

        when(bookCrudOperationsService.getAllBooks()).thenReturn(books);

        String expected = "[{\"id\":0,\"title\":\"W pustyni i w puszczy\",\"author\":\"Henryk Sienkiewicz\",\"category\":\"dramat\",\"available\":true}]";

        MvcResult mvcResult = mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, content);

        verify(bookCrudOperationsService, times(1)).getAllBooks();
    }

    @Test
    public void deleteBook() throws Exception {
        Book bookToDelete = createDummyBook();

        when(bookCrudOperationsService.deleteBook(bookToDelete.getId())).thenReturn(bookToDelete);

        String expected = "{\"id\":0,\"title\":\"W pustyni i w puszczy\",\"author\":\"Henryk Sienkiewicz\",\"category\":\"dramat\",\"available\":true}";

        MvcResult mvcResult = mockMvc.perform(delete("/books")
                .param("id", String.valueOf(0)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, content);

        verify(bookCrudOperationsService, times(1)).deleteBook(bookToDelete.getId());
    }

    @Test
    public void updateBook() throws Exception {
        Book bookToUpdate = createDummyBook();

        when(bookCrudOperationsService.updateBook(any())).thenReturn(bookToUpdate);

        String expected = "{\"id\":0,\"title\":\"W pustyni i w puszczy\",\"author\":\"Henryk Sienkiewicz\",\"category\":\"dramat\",\"available\":true}";

        MvcResult mvcResult = mockMvc.perform(put("/books")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(bookToUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, content);

        verify(bookCrudOperationsService, times(1)).updateBook(any());
    }

    private Book createDummyBook() {
        return new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", LocalDateTime.now(), true);
    }
}