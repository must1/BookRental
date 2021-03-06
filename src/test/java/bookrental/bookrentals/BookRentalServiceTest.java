package bookrental.bookrentals;

import bookrental.account.Account;
import bookrental.book.Book;
import bookrental.account.AccountRepository;
import bookrental.bookrentals.BookRentalService;
import bookrental.bookrentals.BookRentalsRepository;
import bookrental.book.BookRepository;
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
    private BookRentalsRepository bookRentalsRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AccountRepository userRepository;

    @InjectMocks
    private BookRentalService bookRentalService;

    @Test
    public void rentBook() {
        Book book = createDummyBook();
        Account account = createDummyUser();
        account.setAmountOfCashToPay(0);

        when(bookRepository.doesBookExistsWithGivenID(book.getId())).thenReturn(true);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(userRepository.findById(account.getId())).thenReturn(Optional.of(account));

        String expected = "Book was rented";

        assertEquals(expected, bookRentalService.rentBook(account.getId(), book.getId()));

        verify(bookRentalsRepository, times(1)).save(any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void rentBookWhenBookNotExist() {
        Book book = createDummyBook();
        Account user = createDummyUser();

        String expected = "Book does not exist";

        assertEquals(expected, bookRentalService.rentBook(user.getId(), book.getId()));
    }

    private Book createDummyBook() {
        return new Book(1, "W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", true);
    }

    private Account createDummyUser() {
        return new Account(1, "must", "123", 1);
    }
}