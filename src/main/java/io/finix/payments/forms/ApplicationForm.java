package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;
import java.util.Map;

@Builder
public class ApplicationForm implements Form {

  public ApplicationOwnerIdentityEntityForm entity;
  public String user;
  public Long maxTransactionAmount;
  public Map<String, String> tags;

}
