package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class ApplicationProfileForm implements Form {

  public String feeProfile;
  public String riskProfile;
  public Map<String, String> tags;

}
