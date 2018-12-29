package bookrental.service.penalty;

import bookrental.model.account.Account;
import bookrental.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PenaltyPaymentService {

    private final AccountRepository accountRepository;

    @Autowired
    public PenaltyPaymentService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String payPenalty(int accountID) {
        Account user = accountRepository.findById(accountID).orElse(null);
        if (accountRepository.doesAccountExistsWithGivenID(accountID)) {
            long amountOfCashToPay = user.getAmountOfCashToPay();
            if (amountOfCashToPay == 0) {
                return "You have nothing to pay!";
            } else {
                user.setAmountOfCashToPay(0);
                accountRepository.save(user);
                return "Thanks for paying! To next!";
            }
        } else {
            return "Account does not exist";
        }
    }
}
