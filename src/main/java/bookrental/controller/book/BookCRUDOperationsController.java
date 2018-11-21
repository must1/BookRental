package bookrental.controller.book;

import bookrental.model.book.Book;
import bookrental.service.book.BookCRUDOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookCRUDOperationsController {

    private final BookCRUDOperationsService bookOperationsService;

    @Autowired
    public BookCRUDOperationsController(BookCRUDOperationsService bookOperationsService) {
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

    @PutMapping("/books")
    public void updateBook(@RequestBody Book updatedBook) {
        bookOperationsService.updateBook(updatedBook);
    }
}
