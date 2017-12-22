package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class MerchantUnderwritingForm implements Form {

  public String processor;
  public Map<String, String> tags;

}
