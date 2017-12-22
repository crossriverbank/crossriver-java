package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class UserMerchantCreateForm implements Form {

  public Boolean enabled;
  public String identity;
  public Map<String, String> tags;

}
