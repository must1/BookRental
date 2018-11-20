package bookrental.controller.account;

import bookrental.model.book.BookRentals;
import bookrental.service.account.UserRentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRentalsController {

    private final UserRentalsService userRentalsService;

    @Autowired
    public UserRentalsController(UserRentalsService userRentalsService) {
        this.userRentalsService = userRentalsService;
    }

    @GetMapping("books/rentals/{userID}")
    public List<BookRentals> findUserRentalsByGivenID(@PathVariable int userID) {
        return userRentalsService.findUserRentalsByGivenID(userID);
    }
}
