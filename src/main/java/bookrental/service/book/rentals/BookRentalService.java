package bookrental.service.book.rentals;

import bookrental.model.account.User;
import bookrental.model.book.Book;
import bookrental.model.book.BookRentals;
import bookrental.repository.account.UserRepository;
import bookrental.repository.book.BookRepository;
import bookrental.repository.book.BookRentalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookRentalService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookRentalsRepository bookRentalsRepository;

    @Autowired
    public BookRentalService(BookRepository bookRepository, BookRentalsRepository bookRentalsRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.bookRentalsRepository = bookRentalsRepository;
        this.userRepository = userRepository;
    }

    public String rentBook(int userID, int bookID) {
        if (userRepository.doesAccountExistsWithGivenID(userID)) {
            if (bookRepository.doesBookExistsWithGivenID(bookID)) {
                Book bookToRent = bookRepository.findOne(bookID);
                if (bookToRent.isAvailable()) {
                    updateBookAvailabilityAndSaveToDb(bookToRent);
                    BookRentals preparedBookToRent = prepareBookToRent(userID, bookToRent);
                    bookRentalsRepository.save(preparedBookToRent);
                } else {
                    throw new IllegalArgumentException("Book is not available");
                }
            } else {
                throw new IllegalArgumentException("Book does not exist!");
            }
        } else {
            throw new IllegalArgumentException("Account does not exist!");
        }
        return "Book was rented";
    }

    private BookRentals prepareBookToRent(int userID, Book book) {
        return new BookRentals(book, new User(userID));
    }

    private void updateBookAvailabilityAndSaveToDb(Book bookToRent) {
        bookToRent.setAvailable(false);
        bookRepository.save(bookToRent);
    }

    public List<BookRentals> findAllRentals() {
        List<BookRentals> rentedBooks = new ArrayList<>();
        bookRentalsRepository.findAll().forEach(rentedBooks::add);
        return rentedBooks;
    }
}

