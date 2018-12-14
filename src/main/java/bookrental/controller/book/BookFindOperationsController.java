package bookrental.controller.book;

import bookrental.model.book.Book;
import bookrental.service.book.BookFindOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookFindOperationsController {

    private final BookFindOperationsService bookService;

    @Autowired
    public BookFindOperationsController(BookFindOperationsService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/author/{authorID}")
    public List<Book> findBooksByAuthor(@PathVariable String authorID) {
        return bookService.findBooksByAuthor(authorID);
    }

    @GetMapping("/books/category/{categoryID}")
    public List<Book> findBooksByCategory(@PathVariable String categoryID) {
        return bookService.findBooksByCategory(categoryID);
    }

    @GetMapping("/books/title/{titleID}")
    public List<Book> findBooksByTitle(@PathVariable String titleID) {
        return bookService.findBooksByTitle(titleID);
    }

    @GetMapping("/books/filter")
    public List<Book> findBooksByTitleAndAuthorAndCategory(@RequestParam(value = "titleID", required = false) String titleID, @RequestParam(value = "authorID", required = false) String authorID, @RequestParam(value = "categoryID", required = false) String categoryID) {
        return bookService.findAllByTitleAndAuthorAndCategoryIgnoreCase(titleID, authorID, categoryID);
    }
}
