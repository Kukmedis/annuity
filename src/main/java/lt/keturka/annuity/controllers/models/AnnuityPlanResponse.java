package lt.keturka.annuity.controllers.models;

import lombok.Value;
import lt.keturka.annuity.domain.AnnuityPayment;

import java.util.List;

@Value
public class AnnuityPlanResponse {

    private List<AnnuityPayment> schedule;

}
