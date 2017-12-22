package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.enums.DisputeReason;
import io.finix.payments.enums.DisputeState;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.Map;

@Getter
public class Dispute implements View {

  public String application;
  public Map<String,String> tags;
  public Long amount;
  public DisputeState state;
  public String occurredAt;
  public String respondBy;
  public String transfer;
  public DisputeReason reason;
  public String identity;
  public String id;
  public String createdAt;
  public String updatedAt;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
