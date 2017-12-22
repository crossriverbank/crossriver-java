package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.Map;

@Getter
public class RiskProfile implements View {

  public Map<String,String> tags;
  public String application;
  public Boolean avsFailureAllowed;
  public Boolean cscFailureAllowed;
  public String id;
  public String createdAt;
  public String updatedAt;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
