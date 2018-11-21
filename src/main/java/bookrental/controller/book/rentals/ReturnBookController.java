package bookrental.controller.book.rentals;

import bookrental.service.book.rentals.ReturnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnBookController {

    private final ReturnBookService returnBookService;

    @Autowired
    public ReturnBookController(ReturnBookService returnBookService) {
        this.returnBookService = returnBookService;
    }

    @DeleteMapping("books/return")
    public void returnBook(@RequestParam("userID") int userID, @RequestParam("bookID") int bookID) {
        returnBookService.returnBook(userID, bookID);
    }
}

