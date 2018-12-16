package bookrental.service.book.rentals;

import bookrental.model.account.User;
import bookrental.model.book.Book;
import bookrental.model.book.BookRentals;
import bookrental.repository.account.UserRepository;
import bookrental.repository.book.BookRentalsRepository;
import bookrental.repository.book.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReturnBookServiceTest {

    @Mock
    BookRentalsRepository bookRentalsRepository;
    @Mock
    BookRepository bookRepository;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    ReturnBookService returnBookService;

    @Test
    public void returnBook() {
        Book book = createDummyBook();
        User user = createDummyUser();

        when(bookRepository.findOne(book.getId())).thenReturn(book);
        when(bookRentalsRepository.isBookRentedWithGivenIDByUserWithGivenID(book.getId(), user.getId())).thenReturn(true);

        String expected = "Book was returned";

        assertEquals(expected, returnBookService.returnBook(user.getId(), book.getId()));
        verify(bookRentalsRepository, times(1)).delete(book.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void returnBookWhenBookIsNotRented() {
        Book book = createDummyBook();
        User user = createDummyUser();

        when(bookRepository.findOne(book.getId())).thenReturn(book);
        when(bookRentalsRepository.isBookRentedWithGivenIDByUserWithGivenID(book.getId(), user.getId())).thenReturn(false);

        String expected = "Book was not returned";

        assertEquals(expected, returnBookService.returnBook(user.getId(), book.getId()));
    }

    private Book createDummyBook() {
        return new Book(1, "W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", true);
    }

    private User createDummyUser() {
        return new User(1, "must", "123");
    }
}