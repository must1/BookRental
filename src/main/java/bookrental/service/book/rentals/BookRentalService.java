package bookrental.service.book.rentals;

import bookrental.model.account.User;
import bookrental.model.book.Book;
import bookrental.model.book.BookRentals;
import bookrental.repository.book.BookRepository;
import bookrental.repository.book.BookRentalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookRentalService {

    private final BookRepository bookRepository;
    private final BookRentalsRepository bookRentalsRepository;

    @Autowired
    public BookRentalService(BookRepository bookRepository, BookRentalsRepository bookRentalsRepository) {
        this.bookRepository = bookRepository;
        this.bookRentalsRepository = bookRentalsRepository;
    }

    public void rentBook(int userID, int bookID) {
        if (bookRepository.doesBookExistsWithGivenID(bookID)) {
            Book bookToRent = bookRepository.findOne(bookID);
            if (bookToRent.isAvailable()) {
                updateBookAvailabilityAndSaveToDb(bookToRent);
                BookRentals preparedBookToRent = prepareBookToRent(userID, bookID);
                bookRentalsRepository.save(preparedBookToRent);
            } else {
                throw new IllegalArgumentException("Book is no available");
            }
        } else {
            throw new IllegalArgumentException("Book does not exist!");
        }
    }


    private BookRentals prepareBookToRent(int userID, int bookID) {
        return new BookRentals(new Book(bookID), new User(userID));
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

