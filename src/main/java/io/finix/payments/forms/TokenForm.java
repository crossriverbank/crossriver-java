package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;

import java.util.Currency;
import java.util.Map;

public abstract class TokenForm implements Form {

  public String name;
  public String type;
  public Currency currency;
  public Map<String, String> tags;

}
