package bookrental.repository.book;

import bookrental.model.book.BookRentals;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRentalsRepository extends CrudRepository<BookRentals, Integer> {

    @Query("SELECT userRentals FROM BookRentals userRentals WHERE :userID = userRentals.user.id")
    List<BookRentals> getUserRentalsByGivenID(@Param("userID") int userID);

    @Query("SELECT CASE WHEN COUNT(rentedBook) > 0 THEN true ELSE false END FROM BookRentals rentedBook WHERE ((rentedBook.book.id=:bookID) AND  (rentedBook.user.id=:userID))")
    boolean isBookRentedWithGivenIDByUserWithGivenID(@Param("bookID") int bookID, @Param("userID") int userID);

    @Query("SELECT rentedBook FROM BookRentals rentedBook WHERE rentedBook.book.id =:bookID")
    BookRentals getBookBybBookID(@Param("bookID") int bookID);
}