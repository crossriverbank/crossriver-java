package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class Verification implements View {

  public String application;
  public String traceId;
  public String paymentInstrument;
  public String merchant;
  public String identity;
  public Map<String,String> tags;
  public List messages;
  public Object raw;
  public String processor;
  public String state;
  public String id;
  public String createdAt;
  public String updatedAt;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
