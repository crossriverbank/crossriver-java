package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.enums.ResponseMessageType;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class ResponseMessage implements View {

  public ResponseMessageType type;
  public String text;
  public String code;
  public List errors;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
