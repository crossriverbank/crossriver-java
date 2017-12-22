package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class StaleTransfer implements View {

  public UUID transferId;
  public UUID merchantId;
  public Integer daysPending;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
