package bookrental.penalty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PenaltyController {

    private final PenaltyPaymentService penaltyPaymentService;

    @Autowired
    public PenaltyController(PenaltyPaymentService penaltyPaymentService) {
        this.penaltyPaymentService = penaltyPaymentService;
    }

    @PutMapping("paypenalty/{accountID}")
    public String payPenalty(@PathVariable int accountID) {
        return penaltyPaymentService.payPenalty(accountID);
    }
}
