package bookrental.service.penalty;

import bookrental.model.account.Account;
import bookrental.repository.account.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PenaltyPaymentServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private PenaltyPaymentService paymentService;

    @Test
    public void payPenaltyWith0AmountToPay() {
        Account account = createDummyAccount();

        when(accountRepository.doesAccountExistsWithGivenID(account.getId())).thenReturn(true);
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

        String expected = "You have nothing to pay!";

        assertEquals(expected,paymentService.payPenalty(account.getId()));
    }


    @Test
    public void payPenaltyWithSomeAmountToPay() {
        Account account = createDummyAccount();
        account.setAmountOfCashToPay(10);

        when(accountRepository.doesAccountExistsWithGivenID(account.getId())).thenReturn(true);
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

        String expected = "Thanks for paying! To next!";

        assertEquals(expected,paymentService.payPenalty(account.getId()));
        assertEquals(0,account.getAmountOfCashToPay());

        verify(accountRepository,times(1)).save(account);
    }

    @Test(expected = IllegalArgumentException.class)
    public void payPenaltyWithNoAccount() {
        Account account = createDummyAccount();

        when(accountRepository.doesAccountExistsWithGivenID(account.getId())).thenReturn(false);

        String expected = "Account does not exist";

        assertEquals(expected,paymentService.payPenalty(account.getId()));
    }
    private Account createDummyAccount() {
        return new Account(0, "must", "123",0);
    }
}