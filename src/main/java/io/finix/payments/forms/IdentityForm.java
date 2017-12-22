package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class IdentityForm implements Form {

  public IdentityEntityForm entity;
  public Map<String, String> tags;

}
