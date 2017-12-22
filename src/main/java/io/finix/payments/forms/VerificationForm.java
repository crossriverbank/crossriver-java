package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class VerificationForm implements Form {

  public String identityId;
  public String instrumentId;
  public String underwrittenMerchantId;
  public String processor;
  public Map<String, String> tags;

}
