package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.enums.UserRole;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.Map;

@Getter
public class User implements View {

  public String application;
  public String password;
  public String identity;
  public Boolean enabled;
  public UserRole role;
  public Map<String,String> tags;
  public String id;
  public String createdAt;
  public String updatedAt;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
