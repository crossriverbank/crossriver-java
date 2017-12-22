package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

@Builder
public class FeeStrategyForm implements Form {

  public Long fixedAmount;
  public float percentage;

}
