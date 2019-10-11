package lt.keturka.annuity.controllers;

import lt.keturka.annuity.controllers.models.AnnuityPlanResponse;
import lt.keturka.annuity.domain.calc.AnnuityCalculator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.money.MonetaryAmount;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@RestController
@RequestMapping("/annuity-plan")
@Validated
public class AnnuityController {

    private final AnnuityCalculator annuityCalculator;

    public AnnuityController(AnnuityCalculator annuityCalculator) {
        this.annuityCalculator = annuityCalculator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public AnnuityPlanResponse calculatePaymentPlan(
            @RequestParam MonetaryAmount loanAmount,
            @RequestParam Double nominalRate,
            @RequestParam @Min(1) Integer durationInMonths,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        return new AnnuityPlanResponse(annuityCalculator.calculateAnnuityPayments(
                loanAmount, nominalRate / 100, durationInMonths, startDate));
    }

}
