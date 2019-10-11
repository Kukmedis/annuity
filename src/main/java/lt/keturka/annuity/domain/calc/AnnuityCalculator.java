package lt.keturka.annuity.domain.calc;

import lt.keturka.annuity.domain.AnnuityPayment;
import org.javamoney.moneta.function.MonetaryFunctions;
import org.springframework.stereotype.Component;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Component
public class AnnuityCalculator {

    public List<AnnuityPayment> calculateAnnuityPayments(
            MonetaryAmount loanAmount, Double rate, Integer durationInMonths, LocalDate startDate) {
        List<AnnuityPayment> payments = new LinkedList<>();
        double monthlyRate = rate / 12;
        MonetaryAmount currentValue = loanAmount;
        LocalDate date = startDate;
        for (int i = 1; i < durationInMonths; i++) {
            AnnuityPayment ap = calculateAnnuityPayment(
                    loanAmount, durationInMonths, monthlyRate, currentValue, date);
            payments.add(ap);
            currentValue = ap.getRemainingOutstandingPrincipal();
            date = ap.getDate().plusMonths(1);
        }
        payments.add(calculateLastPayment(monthlyRate, currentValue, date));
        return payments;
    }

    private AnnuityPayment calculateAnnuityPayment(
            MonetaryAmount totalValue, Integer durationInMonths, double monthlyRate, MonetaryAmount currentValue,
            LocalDate date) {
        MonetaryAmount payment = totalValue
                .multiply(monthlyRate)
                .divide(1 - Math.pow(1 + monthlyRate, -durationInMonths))
                .with(Monetary.getDefaultRounding());
        MonetaryAmount interest = currentValue
                .multiply(monthlyRate * 12 * 30).divide(360).with(Monetary.getDefaultRounding());
        MonetaryAmount principal = MonetaryFunctions.min().apply(
                payment.subtract(interest), currentValue);
        MonetaryAmount remainingOutstandingPrincipal = currentValue.subtract(payment).add(interest);
        return new AnnuityPayment(
                payment, principal, interest, currentValue, remainingOutstandingPrincipal, date);
    }

    private AnnuityPayment calculateLastPayment(double monthlyRate, MonetaryAmount currentValue, LocalDate date) {
        MonetaryAmount interest = currentValue
                .multiply(monthlyRate * 12 * 30).divide(360).with(Monetary.getDefaultRounding());
        return new AnnuityPayment(
                currentValue.add(interest), currentValue, interest, currentValue, currentValue.multiply(0), date);
    }

}
