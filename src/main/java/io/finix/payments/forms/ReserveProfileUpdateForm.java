package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;
import java.util.Map;

@Builder
public class ReserveProfileUpdateForm implements Form {

  public Integer reservePercentage;
  public Integer reserveDays;
  public Map<String, String> tags;

}
