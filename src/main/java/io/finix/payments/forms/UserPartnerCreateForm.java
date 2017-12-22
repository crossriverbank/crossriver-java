package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class UserPartnerCreateForm implements Form {

  public Boolean enabled;
  public String application;
  public Map<String, String> tags;

}
