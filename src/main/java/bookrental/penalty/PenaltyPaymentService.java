package bookrental.penalty;

import bookrental.account.Account;
import bookrental.account.AccountRepository;
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
        Account account = accountRepository.findById(accountID).orElse(null);
        if (accountRepository.doesAccountExistsWithGivenID(accountID)) {
            assert account != null;
            long amountOfCashToPay = account.getAmountOfCashToPay();
            if (amountOfCashToPay == 0) {
                return "You have nothing to pay!";
            } else {
                account.setAmountOfCashToPay(0);
                accountRepository.save(account);
                return "Thanks for paying! To next!";
            }
        } else {
            throw new IllegalArgumentException("Account does not exist");
        }
    }
}
