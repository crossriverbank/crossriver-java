package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.Currency;
import java.util.Map;

@Getter
public class InstrumentToken implements View {

  public String application;
  public String fingerprint;
  public String instrumentType;
  public String expiresAt;
  public Currency currency;
  public String id;
  public String createdAt;
  public String updatedAt;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
