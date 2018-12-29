package bookrental.controller.book;

import bookrental.model.book.Book;
import bookrental.service.book.BookCrudOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookCrudOperationsController {

    private final BookCrudOperationsService bookOperationsService;

    @Autowired
    public BookCrudOperationsController(BookCrudOperationsService bookOperationsService) {
        this.bookOperationsService = bookOperationsService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookOperationsService.getAllBooks();
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book newBook) {
        return bookOperationsService.createBook(newBook);
    }

    @DeleteMapping("/books")
    public Book deleteBook(@RequestParam int id) {
        return bookOperationsService.deleteBook(id);
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody Book updatedBook) {
        return bookOperationsService.updateBook(updatedBook);
    }
}
