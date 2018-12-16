package bookrental.controller.book;

import bookrental.model.book.Book;
import bookrental.service.book.BookFindOperationsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookFindOperationsController.class)
public class BookFindOperationsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookFindOperationsService bookService;

    @Test
    public void findBooksByAuthor() throws Exception {
        List<Book> books = new ArrayList<>();
        Book book = createDummyBook();
        books.add(book);

        when(bookService.findBooksByAuthor("Henryk Sienkiewicz")).thenReturn(books);

        String expected = "[{\"id\":0,\"title\":\"W pustyni i w puszczy\",\"author\":\"Henryk Sienkiewicz\",\"category\":\"dramat\",\"available\":true}]";

        MvcResult mvcResult = mockMvc.perform(get("/books/author/Henryk Sienkiewicz")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, content);

        verify(bookService, times(1)).findBooksByAuthor(anyString());
    }

    @Test
    public void findBooksByCategory() throws Exception {
        List<Book> books = new ArrayList<>();
        Book book = createDummyBook();
        books.add(book);

        when(bookService.findBooksByCategory("dramat")).thenReturn(books);

        String expected = "[{\"id\":0,\"title\":\"W pustyni i w puszczy\",\"author\":\"Henryk Sienkiewicz\",\"category\":\"dramat\",\"available\":true}]";

        MvcResult mvcResult = mockMvc.perform(get("/books/category/dramat")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, content);

        verify(bookService, times(1)).findBooksByCategory(anyString());
    }

    @Test
    public void findBooksByTitle() throws Exception {
        List<Book> books = new ArrayList<>();
        Book book = createDummyBook();
        books.add(book);

        when(bookService.findBooksByTitle("W pustyni i w puszczy")).thenReturn(books);

        String expected = "[{\"id\":0,\"title\":\"W pustyni i w puszczy\",\"author\":\"Henryk Sienkiewicz\",\"category\":\"dramat\",\"available\":true}]";

        MvcResult mvcResult = mockMvc.perform(get("/books/title/W pustyni i w puszczy")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, content);

        verify(bookService, times(1)).findBooksByTitle(anyString());
    }

    @Test
    public void findBooksByTitleAndAuthorAndCategory() throws Exception {
        List<Book> books = new ArrayList<>();
        Book book = createDummyBook();
        books.add(book);

        when(bookService.findAllByTitleAndAuthorAndCategoryIgnoreCase(anyString(), anyString(), anyString())).thenReturn(books);

        String expected = "[{\"id\":0,\"title\":\"W pustyni i w puszczy\",\"author\":\"Henryk Sienkiewicz\",\"category\":\"dramat\",\"available\":true}]";

        MvcResult mvcResult = mockMvc.perform(get("/books/filter?titleID=W pustyni i w puszczy&authorID=Henryk Sienkiewicz&categoryID=dramat")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, content);

        verify(bookService, times(1)).findAllByTitleAndAuthorAndCategoryIgnoreCase(anyString(), anyString(), anyString());
    }

    @Test
    public void shouldReturnNoBooksByFindBooksByCategory() throws Exception {
        List<Book> books = new ArrayList<>();
        Book book = createDummyBook();
        books.add(book);

        when(bookService.findBooksByCategory("dramat")).thenReturn(books);

        String expected = "[]";

        MvcResult mvcResult = mockMvc.perform(get("/books/category/test")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, content);

        verify(bookService, times(1)).findBooksByCategory(anyString());
    }

    private Book createDummyBook() {
        return new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", true);
    }
}