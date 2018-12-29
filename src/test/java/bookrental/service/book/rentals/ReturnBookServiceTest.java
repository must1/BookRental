package bookrental.service.book.rentals;

import bookrental.model.account.Account;
import bookrental.model.book.Book;
import bookrental.repository.account.AccountRepository;
import bookrental.repository.book.BookRentalsRepository;
import bookrental.repository.book.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ReturnBookServiceTest {

    @Mock
    BookRentalsRepository bookRentalsRepository;
    @Mock
    BookRepository bookRepository;
    @Mock
    AccountRepository userRepository;

    @InjectMocks
    ReturnBookService returnBookService;

    @Test
    public void returnBook() {
        Book book = createDummyBook();
        Account user = createDummyUser();

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRentalsRepository.isBookRentedWithGivenIDByAccountWithGivenID(book.getId(), user.getId())).thenReturn(true);

        String expected = "Book was returned";

        assertEquals(expected, returnBookService.returnBook(user.getId(), book.getId()));
        verify(bookRentalsRepository, times(1)).deleteById(book.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void returnBookWhenBookIsNotRented() {
        Book book = createDummyBook();
        Account user = createDummyUser();

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRentalsRepository.isBookRentedWithGivenIDByAccountWithGivenID(book.getId(), user.getId())).thenReturn(false);

        String expected = "Book was not returned";

        assertEquals(expected, returnBookService.returnBook(user.getId(), book.getId()));
    }

    private Book createDummyBook() {
        return new Book(1, "W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", true);
    }

    private Account createDummyUser() {
        return new Account(1, "must", "123",0);
    }
}