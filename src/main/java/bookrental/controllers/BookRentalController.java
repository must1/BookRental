package bookrental.controllers;

import bookrental.bookrentals.BookRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookRentalController {

    private final BookRentalService bookRentalService;

    @Autowired
    public BookRentalController(BookRentalService bookRentalService) {
        this.bookRentalService = bookRentalService;
    }

    @PostMapping("/books/rent")
    public String rentBook(@RequestParam("accountID") int accountID, @RequestParam("bookID") int bookID) {
        return bookRentalService.rentBook(accountID, bookID);
    }

    @GetMapping(value = "/books/rentals", produces = "application/json")
    public ResponseEntity<String> findAllRentals() {
        return bookRentalService.findAllRentals();
    }
}
