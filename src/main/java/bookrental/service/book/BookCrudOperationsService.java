package bookrental.service.book;

import bookrental.model.book.Book;
import bookrental.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCrudOperationsService {

    private final BookRepository bookRepository;

    @Autowired
    public BookCrudOperationsService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    public Book deleteBook(int id) {
        Book bookToDelete = bookRepository.findOne(id);
        bookRepository.delete(id);
        return bookToDelete;
    }

    public Book updateBook(Book updatedBook) {
        return bookRepository.save(updatedBook);
    }
}
