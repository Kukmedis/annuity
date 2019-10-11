package lt.keturka.annuity.domain;

import lombok.Value;

import javax.money.MonetaryAmount;
import java.time.LocalDate;

@Value
public class AnnuityPayment {

    private MonetaryAmount borrowerPayment;

    private MonetaryAmount principal;

    private MonetaryAmount interest;

    private MonetaryAmount initialOutstandingPrincipal;

    private MonetaryAmount remainingOutstandingPrincipal;

    private LocalDate date;

}
