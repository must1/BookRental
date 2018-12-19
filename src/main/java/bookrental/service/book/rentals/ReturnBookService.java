package bookrental.service.book.rentals;

import bookrental.model.book.Book;
import bookrental.repository.account.UserRepository;
import bookrental.repository.book.BookRentalsRepository;
import bookrental.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnBookService {
    private final BookRentalsRepository bookRentalsRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReturnBookService(BookRentalsRepository bookRentalsRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.bookRentalsRepository = bookRentalsRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public String returnBook(int userID, int bookID) {
        if (userRepository.doesAccountExistsWithGivenID(userID)) {
            if (bookRentalsRepository.isBookRentedWithGivenIDByUserWithGivenID(bookID, userID)) {
                Book bookToReturn = bookRepository.findById(bookID).orElse(null);
                updateBookAvailabilityAndSaveToDb(bookToReturn);
                bookRentalsRepository.deleteById(bookID);
            } else {
                throw new IllegalArgumentException("Book was not rented!");
            }
        } else {
            throw new IllegalArgumentException("Account does not exist");
        }
        return "Book was returned";
    }

    private void updateBookAvailabilityAndSaveToDb(Book bookToReturn) {
        bookToReturn.setAvailable(true);
        bookRepository.save(bookToReturn);
    }
}