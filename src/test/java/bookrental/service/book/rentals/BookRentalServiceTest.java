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
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookRentalServiceTest {

    @Mock
    public BookRentalsRepository bookRentalsRepository;
    @Mock
    public BookRepository bookRepository;
    @Mock
    public AccountRepository userRepository;

    @InjectMocks
    public BookRentalService bookRentalService;

    @Test
    public void rentBook() {
        Book book = createDummyBook();
        Account user = createDummyUser();

        when(bookRepository.doesBookExistsWithGivenID(book.getId())).thenReturn(true);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(userRepository.doesAccountExistsWithGivenID(user.getId())).thenReturn(true);

        String expected = "Book was rented";

        assertEquals(expected, bookRentalService.rentBook(user.getId(), book.getId()));

        verify(bookRentalsRepository, times(1)).save(any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void rentBookWhenBookNotExist() {
        Book book = createDummyBook();
        Account user = createDummyUser();

        when(bookRepository.doesBookExistsWithGivenID(book.getId())).thenReturn(false);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(userRepository.doesAccountExistsWithGivenID(user.getId())).thenReturn(true);

        String expected = "Book does not exist";

        assertEquals(expected, bookRentalService.rentBook(user.getId(), book.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void rentBookWhenAccountNotExists() {
        Book book = createDummyBook();
        Account user = createDummyUser();

        when(bookRepository.doesBookExistsWithGivenID(book.getId())).thenReturn(true);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(userRepository.doesAccountExistsWithGivenID(user.getId())).thenReturn(false);

        bookRentalService.rentBook(user.getId(), book.getId());
    }

    private Book createDummyBook() {
        return new Book(1, "W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", true);
    }

    private Account createDummyUser() {
        return new Account(1, "must", "123", 1);
    }
}