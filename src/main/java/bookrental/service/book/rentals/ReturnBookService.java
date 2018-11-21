package bookrental.service.book.rentals;

import bookrental.model.account.User;
import bookrental.model.book.Book;
import bookrental.model.book.BookRentals;
import bookrental.repository.book.BookRentalsRepository;
import bookrental.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnBookService {
    private final BookRentalsRepository bookRentalsRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ReturnBookService(BookRentalsRepository bookRentalsRepository, BookRepository bookRepository) {
        this.bookRentalsRepository = bookRentalsRepository;
        this.bookRepository = bookRepository;
    }

    public void returnBook(int userID, int bookID) {
        if (bookRentalsRepository.isBookRentedWithGivenIDByUserWithGivenID(bookID, userID)) {
            Book bookToReturn = bookRepository.findOne(bookID);
            updateBookAvailabilityAndSaveToDb(bookToReturn);
            bookRentalsRepository.delete(bookID);
        } else {
            throw new IllegalArgumentException("Book was not rented!");
        }
    }

    private void updateBookAvailabilityAndSaveToDb(Book bookToReturn) {
        bookToReturn.setAvailable(true);
        bookRepository.save(bookToReturn);
    }
}
