package bookrental.book;

import bookrental.book.Book;
import bookrental.book.BookFindOperationsService;
import bookrental.book.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookFindOperationsServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookFindOperationsService bookFindOperationsService;

    @Test
    public void findBooksByAuthor() {
        Book book = createDummyBook();
        List<Book> books = new ArrayList<>();
        books.add(book);

        when(bookRepository.findByAuthorAllIgnoreCase("Henryk Sienkiewicz")).thenReturn(books);

        assertEquals(1, bookFindOperationsService.findBooksByAuthor("Henryk Sienkiewicz").size());
    }

    @Test
    public void findBooksByCategory() {
        Book book = createDummyBook();
        List<Book> books = new ArrayList<>();
        books.add(book);

        when(bookRepository.findByCategoryAllIgnoreCase("dramat")).thenReturn(books);

        assertEquals(1, bookFindOperationsService.findBooksByCategory("dramat").size());
    }

    @Test
    public void findBooksByTitle() {
        Book book = createDummyBook();
        List<Book> books = new ArrayList<>();
        books.add(book);

        when(bookRepository.findByTitleAllIgnoreCase("W pustyni i w puszczy")).thenReturn(books);

        assertEquals(1, bookFindOperationsService.findBooksByTitle("W pustyni i w puszczy").size());
    }

    @Test
    public void findAllByTitleAndAuthorAndCategoryIgnoreCase() {
        Book book = createDummyBook();
        List<Book> books = new ArrayList<>();
        books.add(book);

        when(bookRepository.findAllByTitleAndAuthorAndCategoryIgnoreCase("W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat")).thenReturn(books);

        assertEquals(1, bookFindOperationsService.findAllByTitleAndAuthorAndCategoryIgnoreCase("W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat").size());
    }

    private Book createDummyBook() {
        return new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", true);
    }
}