package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class AuthorizationUpdateForm implements Form {

  public Long captureAmount;
  public Boolean voidMe;
  public Long fee;
  public String statementDescriptor;
  public Map<String, String> tags;

}
