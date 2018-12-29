package bookrental.service.account;

import bookrental.model.book.BookRentals;
import bookrental.repository.book.BookRentalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRentalsService {

    private final BookRentalsRepository bookRentalsRepository;

    @Autowired
    public AccountRentalsService(BookRentalsRepository bookRentalsRepository) {
        this.bookRentalsRepository = bookRentalsRepository;
    }

    public List<BookRentals> findAccountRentalsByGivenID(int accountID) {
        return bookRentalsRepository.getAccountRentalsByGivenID(accountID);
    }
}
