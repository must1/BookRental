package bookrental.controller.book;

import bookrental.model.book.Book;
import bookrental.service.book.BookCrudOperationsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookCrudOperationsController.class)
public class BookCRUDOperationsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookCrudOperationsService bookCrudOperationsService;

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
    }

    @Test
    public void deleteBook() throws Exception {
        Book bookToDelete = new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", true);

        when(bookCrudOperationsService.deleteBook(anyInt())).thenReturn(bookToDelete);

        String expected = "{\"id\":0,\"title\":\"W pustyni i w puszczy\",\"author\":\"Henryk Sienkiewicz\",\"category\":\"dramat\",\"available\":true}";

        MvcResult mvcResult = mockMvc.perform(delete("/books")
                .param("id", String.valueOf(0)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, content);
    }

    @Test
    public void updateBook() throws Exception {
        Book bookToUpdate = new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", true);

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
    }
}