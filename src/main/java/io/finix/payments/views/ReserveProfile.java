package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.Map;

@Getter
public class ReserveProfile implements View {

  public Map<String,String> tags;
  public String application;
  public Integer reservePercentage;
  public Integer reserveDays;
  public String id;
  public String createdAt;
  public String updatedAt;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
