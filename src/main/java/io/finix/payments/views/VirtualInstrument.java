package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.enums.AccountType;
import java.util.Map;
import lombok.Getter;

@Getter
public class VirtualInstrument extends Instrument {

  public AccountType availableAccountType;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
