package bookrental.service.penalty;

import bookrental.model.account.User;
import bookrental.repository.account.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PenaltyPaymentService {

    private final UserRepository userRepository;

    @Autowired
    public PenaltyPaymentService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String payPenalty(int userID) {
        User user = userRepository.findById(userID).orElse(null);
        if (userRepository.doesAccountExistsWithGivenID(userID)) {
            long amountOfCashToPay = user.getAmountOfCashToPay();
            if (amountOfCashToPay == 0) {
                return "You have nothing to pay!";
            } else {
                user.setAmountOfCashToPay(0);
                userRepository.save(user);
                return "Thanks for paying! To next!";
            }
        } else {
            return "Account does not exist";
        }
    }
}
