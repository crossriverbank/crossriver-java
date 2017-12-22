package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Map;

@Getter
public class BankAccount extends Instrument {

  public String bankCode;
  public String country;
  public String maskedAccountNumber;
  public String name;
  public String accountType;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
