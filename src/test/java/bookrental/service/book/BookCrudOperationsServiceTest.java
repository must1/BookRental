package bookrental.service.book;

import bookrental.model.book.Book;
import bookrental.repository.book.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookCrudOperationsServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookCrudOperationsService bookCrudOperationsService;

    private Book createDummyBook() {
        return new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", true);
    }

    @Test
    public void createBook() {
        Book book = createDummyBook();

        when(bookRepository.save(book)).thenReturn(book);

        assertEquals(book, bookCrudOperationsService.createBook(book));

        verify(bookRepository).save(book);
    }

    @Test
    public void deleteBook() {
        Book book = createDummyBook();

        bookCrudOperationsService.deleteBook(book.getId());

        verify(bookRepository, times(1)).deleteById(book.getId());
    }

    @Test
    public void updateBook() {
        Book book = createDummyBook();

        when(bookRepository.save(book)).thenReturn(book);

        assertEquals(book, bookCrudOperationsService.updateBook(book));

        verify(bookRepository).save(book);
    }

    @Test
    public void getAllBooks() {
        Book book = createDummyBook();
        List<Book> books = new ArrayList<>();
        books.add(book);

        when(bookRepository.findAll()).thenReturn(books);

        assertEquals(books, bookCrudOperationsService.getAllBooks());

        verify(bookRepository).findAll();
    }
}