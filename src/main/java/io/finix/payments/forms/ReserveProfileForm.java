package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class ReserveProfileForm implements Form {

  public String application;
  public Integer reservePercentage;
  public Integer reserveDays;
  public Map<String, String> tags;

}
