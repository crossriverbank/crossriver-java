package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.Map;

@Getter
public class Merchant implements View {

  public String application;
  public String identity;
  public String verification;
  public String merchantProfile;
  public String processor;
  public String onboardingState;
  public Boolean processingEnabled;
  public Boolean settlementEnabled;
  public Map<String,String> tags;
  public String id;
  public String createdAt;
  public String updatedAt;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
