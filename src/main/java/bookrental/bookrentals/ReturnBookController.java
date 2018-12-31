package bookrental.bookrentals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public String returnBook(@RequestParam("accountID") int accountID, @RequestParam("bookID") int bookID) {
        return returnBookService.returnBook(accountID, bookID);
    }
}

