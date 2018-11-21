package bookrental.service.book;

import bookrental.model.book.Book;
import bookrental.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCRUDOperationsService {

    private final BookRepository bookRepository;

    @Autowired
    public BookCRUDOperationsService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(Book newBook) {
        bookRepository.save(newBook);
    }

    public void deleteBook(int id) {
        bookRepository.delete(id);
    }

    public void updateBook(Book updatedBook) {
        bookRepository.save(updatedBook);
    }
}
