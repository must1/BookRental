package bookrental.repository.book;

import bookrental.model.book.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findByAuthorAllIgnoreCase(String authorID);

    List<Book> findByCategoryAllIgnoreCase(String categoryID);

    List<Book> findByTitleAllIgnoreCase(String titleID);

    @Query("SELECT CASE WHEN COUNT(book) > 0 THEN true ELSE false END FROM Book book WHERE book.id =:bookID")
    boolean doesBookExistsWithGivenID(@Param("bookID") int bookID);

    @Query("select a from Book a where  (a.title=:title or :title is null) AND (a.author=:author or :author is null) AND (a.category=:category or :category is null)")
    List<Book> findAllByTitleAndAuthorAndCategoryIgnoreCase(@Param("title") String titleID, @Param("author") String authorID, @Param("category") String categoryID);
}