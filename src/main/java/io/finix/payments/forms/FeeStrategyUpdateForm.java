package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class FeeStrategyUpdateForm implements Form {

  public Long fixedAmount;
  public Float percentage;
  public Map<String, String> tags;

}
