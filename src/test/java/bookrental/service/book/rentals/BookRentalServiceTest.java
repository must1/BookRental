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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookRentalServiceTest {

    @Mock
    BookRentalsRepository bookRentalsRepository;
    @Mock
    BookRepository bookRepository;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    BookRentalService bookRentalService;

    @Test
    public void rentBook() {
        Book book = createDummyBook();
        User user = createDummyUser();

        when(bookRepository.doesBookExistsWithGivenID(book.getId())).thenReturn(true);
        when(bookRepository.findOne(book.getId())).thenReturn(book);
        when(userRepository.doesAccountExistsWithGivenID(user.getId())).thenReturn(true);
        when(userRepository.findOne(user.getId())).thenReturn(user);

        String expected = "Book was rented";

        assertEquals(expected, bookRentalService.rentBook(user.getId(), book.getId()));

        verify(bookRentalsRepository, times(1)).save((BookRentals) any());
    }

    @Test
    public void findAllRentals() {
    }

    private Book createDummyBook() {
        return new Book(1, "W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", true);
    }

    private User createDummyUser() {
        return new User(1, "must", "123");
    }
}