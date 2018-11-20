package bookrental.service.book;

import bookrental.model.book.Book;
import bookrental.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    public List<Book> findBooksByAuthor(String authorID) {
        return bookRepository.findByAuthorAllIgnoreCase(authorID);
    }

    public List<Book> findBooksByCategory(String categoryID) {
        return bookRepository.findByCategoryAllIgnoreCase(categoryID);
    }

    public List<Book> findBooksByTitle(String titleID) {
        return bookRepository.findByTitleAllIgnoreCase(titleID);
    }

    public List<Book> findAllByTitleAndAuthorAndCategoryIgnoreCase(String titleID, String authorID, String categoryID) {
        return bookRepository.findAllByTitleAndAuthorAndCategoryIgnoreCase( titleID,authorID,categoryID);
    }
}