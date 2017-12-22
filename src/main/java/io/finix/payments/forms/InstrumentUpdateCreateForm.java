package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class InstrumentUpdateCreateForm implements Form {

  public String merchantId;
  public String instrumentToUpdate;
  public Map<String, String> tags;

}
