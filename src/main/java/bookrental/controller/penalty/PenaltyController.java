package bookrental.controller.penalty;

import bookrental.service.penalty.PenaltyPaymentService;
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

    @PutMapping("paypenalty/{userID}")
    public String payPenalty(@PathVariable int userID) {
        return penaltyPaymentService.payPenalty(userID);
    }
}
