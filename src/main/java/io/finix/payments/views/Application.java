package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.Map;

@Getter
public class Application implements View {

  public Boolean enabled;
  public Map<String, String> tags;
  public String owner;
  public Boolean processingEnabled;
  public Boolean settlementEnabled;
  public String id;
  public String createdAt;
  public String updatedAt;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
