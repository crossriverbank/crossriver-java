package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class InstrumentUpdate implements View {

  public String application;
  public String merchant;
  public String paymentInstrument;
  public String traceId;
  public String state;
  public List messages;
  public String id;
  public String createdAt;
  public String updatedAt;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
