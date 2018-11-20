package bookrental.controller.book;

import bookrental.model.account.User;
import bookrental.model.book.Book;
import bookrental.service.book.BookOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookOperationsController {

    private final BookOperationsService bookOperationsService;

    @Autowired
    public BookOperationsController(BookOperationsService bookOperationsService) {
        this.bookOperationsService = bookOperationsService;
    }

    @PostMapping("/books")
    public void createBook(@RequestBody Book newBook) {
        bookOperationsService.createBook(newBook);
    }

    @DeleteMapping("/books")
    public void deleteBook(@RequestParam int id) {
        bookOperationsService.deleteBook(id);
    }

}
