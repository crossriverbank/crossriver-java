package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Currency;
import java.util.List;
import java.util.Map;

@Builder
public class SettlementForm implements Form {

  public String identity;
  public List includedTransfers;
  public List excludedTransfers;
  public String processorType;
  public Currency currency;
  public String applicationId;
  public Map<String, String> tags;

}
