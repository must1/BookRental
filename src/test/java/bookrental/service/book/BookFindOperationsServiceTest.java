package bookrental.service.book;

import bookrental.model.book.Book;
import bookrental.repository.book.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookFindOperationsServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookFindOperationsService bookFindOperationsService;

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

    }

    @Test
    public void findBooksByTitle() {

    }

    @Test
    public void findAllByTitleAndAuthorAndCategoryIgnoreCase() {
    }

    private Book createDummyBook() {
        return new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", true);
    }
}