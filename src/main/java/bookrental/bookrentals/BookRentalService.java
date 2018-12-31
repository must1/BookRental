package bookrental.bookrentals;

import bookrental.account.Account;
import bookrental.model.book.Book;
import bookrental.model.book.BookRentals;
import bookrental.account.AccountRepository;
import bookrental.book.BookRepository;
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

    private final AccountRepository accountRepository;
    private final BookRepository bookRepository;
    private final BookRentalsRepository bookRentalsRepository;

    @Autowired
    public BookRentalService(BookRepository bookRepository, BookRentalsRepository bookRentalsRepository, AccountRepository accountRepository) {
        this.bookRepository = bookRepository;
        this.bookRentalsRepository = bookRentalsRepository;
        this.accountRepository = accountRepository;
    }

    public String rentBook(int accountID, int bookID) {
        Account account = accountRepository.findById(accountID)
                .orElseThrow(() -> new IllegalArgumentException("Account does not exist!"));

        if (account.getAmountOfCashToPay() == 0) {
            if (bookRepository.doesBookExistsWithGivenID(bookID)) {
                Book bookToRent = bookRepository.findById(bookID)
                        .orElseThrow(() -> new IllegalArgumentException("Book does not exist!"));

                if (bookToRent.isAvailable()) {
                    updateBookAvailabilityAndSaveToDb(bookToRent);
                    BookRentals preparedBookToRent = prepareBookToRent(accountID, bookToRent);
                    bookRentalsRepository.save(preparedBookToRent);
                } else {
                    throw new IllegalArgumentException("Book is not available");
                }
            }
        } else {
            throw new IllegalArgumentException("Please go to paypenalty/accountID to settle the arrears, otherwise you can not rent more books!");
        }

        return "Book was rented";
    }

    private BookRentals prepareBookToRent(int accountID, Book book) {
        BookRentals bookRentals = new BookRentals(book, new Account(accountID));
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
        return new ResponseEntity<>(new JSONSerializer()
                .exclude("book.available")
                .exclude("dateOfReturn")
                .exclude("*.class")
                .exclude("account.amountOfCashToPay")
                .exclude("password")
                .serialize(rentedBooks), headers, HttpStatus.OK);
    }
}

