package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Currency;
import java.util.Map;

@Builder
public class AuthorizationCreateForm implements Form {

  public Long amount;
  public Currency currency;
  public String statementDescriptor;
  public String source;
  public String merchantIdentity;
  public String idempotencyId;
  public String processor;
  public Map<String, String> tags;

}
