package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.Currency;
import java.util.List;
import java.util.Map;

@Getter
public class Authorization implements View {

  public String application;
  public Long amount;
  public Map<String,String> tags;
  public String state;
  public String traceId;
  public Currency currency;
  public String source;
  public String transfer;
  public String merchantIdentity;
  public Boolean isVoid;
  public String expiresAt;
  public String idempotencyId;
  public List messages;
  public Object raw;
  public String id;
  public String createdAt;
  public String updatedAt;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
