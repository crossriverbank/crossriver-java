package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.Currency;
import java.util.List;
import java.util.Map;

@Getter
public class Transfer implements View {

  public Long amount;
  public Map<String,String> tags;
  public String state;
  public String traceId;
  public Currency currency;
  public String application;
  public String source;
  public String destination;
  public String readyToSettleAt;
  public Long fee;
  public String statementDescriptor;
  public String idempotencyId;
  public String merchantIdentity;
  public String type;
  public String subtype;
  public String originalTransfer;
  public List messages;
  public Object raw;
  public String id;
  public String createdAt;
  public String updatedAt;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
