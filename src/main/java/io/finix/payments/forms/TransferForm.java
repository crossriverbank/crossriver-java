package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Currency;
import java.util.Map;

@Builder
public class TransferForm implements Form {

  public String source;
  public String destination;
  public String identity;
  public String merchantIdentity;
  public Long amount;
  public Currency currency;
  public String processorType;
  public Long fee;
  public String statementDescriptor;
  public String idempotencyId;
  public Map<String, String> tags;

}
