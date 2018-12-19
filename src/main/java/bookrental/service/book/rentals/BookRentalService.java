package bookrental.service.book.rentals;

import bookrental.model.account.User;
import bookrental.model.book.Book;
import bookrental.model.book.BookRentals;
import bookrental.repository.account.UserRepository;
import bookrental.repository.book.BookRepository;
import bookrental.repository.book.BookRentalsRepository;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                Book bookToRent = bookRepository.findById(bookID).orElse(null);
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
        BookRentals bookRentals = new BookRentals(book, new User(userID));
        bookRentals.setDateOfRental(LocalDateTime.now());
        return bookRentals;
    }

    private void updateBookAvailabilityAndSaveToDb(Book bookToRent) {
        bookToRent.setAvailable(false);
        bookRepository.save(bookToRent);
    }

    public ResponseEntity<String> findAllRentals() {
        List<BookRentals> rentedBooks = new ArrayList<>();
        bookRentalsRepository.findAll().forEach(rentedBooks::add);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(new JSONSerializer().exclude("book.class")
                .exclude("book.available")
                .exclude("*.class")
                .serialize(rentedBooks), headers, HttpStatus.OK);
    }
}

