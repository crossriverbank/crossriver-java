package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.Currency;
import java.util.Map;

@Getter
public class Settlement implements View {

  public String application;
  public Map<String,String> tags;
  public String processor;
  public String identity;
  public Long totalAmount;
  public Long totalFees;
  public Long totalFee;
  public Long netAmount;
  public String destination;
  public Currency currency;
  public String id;
  public String createdAt;
  public String updatedAt;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
