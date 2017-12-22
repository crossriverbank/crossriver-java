package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class SettlementUpdateForm implements Form {

  public String settlement;
  public String destination;
  public Map<String, String> tags;

}
