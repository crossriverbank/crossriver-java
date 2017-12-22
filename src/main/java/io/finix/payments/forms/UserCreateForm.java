package io.finix.payments.forms;

import io.finix.payments.enums.UserRole;
import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class UserCreateForm implements Form {

  public Boolean enabled;
  public UserRole role;
  public Map<String, String> tags;

}
