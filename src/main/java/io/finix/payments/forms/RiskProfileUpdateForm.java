package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class RiskProfileUpdateForm implements Form {

  public Boolean avsFailureAllowed;
  public Boolean cscFailureAllowed;
  public Map<String, String> tags;

}
