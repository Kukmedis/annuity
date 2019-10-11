package lt.keturka.annuity.domain.calc;

import lt.keturka.annuity.domain.AnnuityPayment;
import org.assertj.core.util.Lists;
import org.javamoney.moneta.Money;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class AnnuityCalculatorTest {

    private AnnuityCalculator annuityCalculator = new AnnuityCalculator();

    @Test
    public void shouldCalculateForOneMonth() {

        List<AnnuityPayment> plan = annuityCalculator.calculateAnnuityPayments(
                Money.of(1000, "EUR"), 0.06, 1, LocalDate.parse("2000-01-01"));

        assertThat(plan, equalTo(Lists.list(
                createAnnuity(1005, 1000, 5, 1000, 0, "2000-01-01")
        )));
    }

    @Test
    public void shouldCalculateForTwoMonths() {
        List<AnnuityPayment> plan = annuityCalculator.calculateAnnuityPayments(
                Money.of(1000, "EUR"), 0.06, 2, LocalDate.parse("2000-01-01"));

        assertThat(plan, equalTo(Lists.list(
                createAnnuity(503.75, 498.75, 5, 1000, 501.25, "2000-01-01"),
                createAnnuity(503.76, 501.25, 2.51, 501.25, 0, "2000-02-01")
        )));
    }

    @Test
    public void shouldCalculateForMultipleMonths() {
        List<AnnuityPayment> plan = annuityCalculator.calculateAnnuityPayments(
                Money.of(10000, "EUR"), 0.10, 3, LocalDate.parse("2000-01-01"));

        assertThat(plan, equalTo(Lists.list(
                createAnnuity(3389.04, 3305.71, 83.33, 10000, 6694.29, "2000-01-01"),
                createAnnuity(3389.04, 3333.25, 55.79, 6694.29, 3361.04, "2000-02-01"),
                createAnnuity(3389.05, 3361.04, 28.01, 3361.04, 0, "2000-03-01")
        )));
    }

    @Test
    public void shouldCalculateForNegativeRate() {
        List<AnnuityPayment> plan = annuityCalculator.calculateAnnuityPayments(
                Money.of(1000, "EUR"), -0.06, 2, LocalDate.parse("2000-01-01"));

        assertThat(plan, equalTo(Lists.list(
                createAnnuity(496.25, 501.25, -5, 1000, 498.75, "2000-01-01"),
                createAnnuity(496.26, 498.75, -2.49, 498.75, 0, "2000-02-01")
        )));
    }

    private AnnuityPayment createAnnuity(
            double borrowerPayment, double principal, double interest, double initialOutstandingPrincipal,
            double remainingOutstandingPrincipal, String date) {
        return new AnnuityPayment(
                Money.of(borrowerPayment, "EUR"),
                Money.of(principal, "EUR"),
                Money.of(interest, "EUR"),
                Money.of(initialOutstandingPrincipal, "EUR"),
                Money.of(remainingOutstandingPrincipal, "EUR"),
                LocalDate.parse(date)
        );
    }
}