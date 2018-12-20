package bookrental.service.book.rentals;

import bookrental.model.account.User;
import bookrental.model.book.Book;
import bookrental.model.book.BookRentals;
import bookrental.repository.account.UserRepository;
import bookrental.repository.book.BookRentalsRepository;
import bookrental.repository.book.BookRepository;
import bookrental.service.penalty.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReturnBookService {
    private final BookRentalsRepository bookRentalsRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PenaltyService penaltyCheckerService;

    @Autowired
    public ReturnBookService(BookRentalsRepository bookRentalsRepository, BookRepository bookRepository, UserRepository userRepository, PenaltyService penaltyCheckerService) {
        this.bookRentalsRepository = bookRentalsRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.penaltyCheckerService = penaltyCheckerService;
    }

    public String returnBook(int userID, int bookID) {
        if (bookRentalsRepository.isBookRentedWithGivenIDByUserWithGivenID(bookID, userID)) {
            BookRentals bookToReturn = bookRentalsRepository.getBookBybBookID(bookID);
            userRepository.findById(userID).ifPresent(user -> {
                BookRentals preparedBookToReturn = prepareBookToReturn(bookToReturn);
                penaltyCheckerService.executePenaltyProcess(bookToReturn, user);
                updateBookAvailabilityAndSaveToDb(bookID);
                bookRentalsRepository.delete(preparedBookToReturn);
            });
        } else {
            throw new IllegalArgumentException("Book was not rented!");
        }
        return "Book was returned";
    }

    private BookRentals prepareBookToReturn(BookRentals preparedBookToReturn) {
        assert preparedBookToReturn != null;
        preparedBookToReturn.setDateOfReturn(LocalDateTime.now());
        return preparedBookToReturn;
    }

    private void updateBookAvailabilityAndSaveToDb(int bookID) {
        bookRepository.findById(bookID).ifPresent(book ->
        {
            book.setAvailable(true);
            bookRepository.save(book);
        });
    }
}