package bookrental.repository.book;

import bookrental.model.book.BookRentals;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRentalsRepository extends CrudRepository<BookRentals, Integer> {

    @Query("SELECT userRentals FROM BookRentals userRentals WHERE :userID = userRentals.user.id")
    List<BookRentals> getUserRentalsByGivenID(@Param("userID") int userID);
}