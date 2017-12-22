package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class RiskProfileForm implements Form {

  public String application;
  public Boolean avsFailureAllowed;
  public Boolean cscFailureAllowed;
  public Map<String, String> tags;

}
