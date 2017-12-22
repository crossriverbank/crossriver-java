package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class MerchantUnderwritingUpdateForm implements Form {

  public Boolean processingEnabled;
  public Boolean settlementEnabled;
  public Map<String, String> tags;

}
