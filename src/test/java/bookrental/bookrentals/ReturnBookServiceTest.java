package bookrental.bookrentals;

import bookrental.account.Account;
import bookrental.book.Book;
import bookrental.account.AccountRepository;
import bookrental.bookrentals.ReturnBookService;
import bookrental.bookrentals.BookRentalsRepository;
import bookrental.book.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ReturnBookServiceTest {

    @Mock
    private BookRentalsRepository bookRentalsRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AccountRepository userRepository;

    @InjectMocks
    ReturnBookService returnBookService;

    @Test
    public void returnBook() {
        Book book = createDummyBook();
        Account user = createDummyUser();

        when(bookRentalsRepository.isBookRentedWithGivenIDByAccountWithGivenID(book.getId(), user.getId())).thenReturn(true);

        String expected = "Book was returned";

        assertEquals(expected, returnBookService.returnBook(user.getId(), book.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void returnBookWhenBookIsNotRented() {
        Book book = createDummyBook();
        Account user = createDummyUser();

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