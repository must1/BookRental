package bookrental.controller.book.rentals;

import bookrental.service.book.rentals.BookRentalService;
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
    public String rentBook(@RequestParam("userID") int userID, @RequestParam("bookID") int bookID) {
        return bookRentalService.rentBook(userID, bookID);
    }

    @GetMapping(value = "/books/rentals", produces = "application/json")
    public ResponseEntity<String> findAllRentals() {
        return bookRentalService.findAllRentals();
    }
}
