package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class MerchantProfileUpdateForm implements Form {

  public String feeProfile;
  public String platformFeeProfile;
  public String riskProfile;
  public String reserveProfile;
  public Map<String, String> tags;

}
