package bookrental.repository.book;

import bookrental.model.book.BookRentals;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRentalsRepository extends CrudRepository<BookRentals, Integer> {

    @Query("SELECT accountRentals FROM BookRentals accountRentals WHERE :accountID = accountRentals.account.id")
    List<BookRentals> getAccountRentalsByGivenID(@Param("accountID") int accountID);

    @Query("SELECT CASE WHEN COUNT(rentedBook) > 0 THEN true ELSE false END FROM BookRentals rentedBook WHERE ((rentedBook.book.id=:bookID) AND  (rentedBook.account.id=:accountID))")
    boolean isBookRentedWithGivenIDByAccountWithGivenID(@Param("bookID") int bookID, @Param("accountID") int accountID);

    @Query("SELECT rentedBook FROM BookRentals rentedBook WHERE rentedBook.book.id =:bookID")
    BookRentals getBookBybBookID(@Param("bookID") int bookID);
}
