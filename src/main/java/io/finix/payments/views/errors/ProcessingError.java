package io.finix.payments.views.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProcessingError {

  public String logref;
  public String message;
  public String code;
  public String field;
  @JsonProperty("_links")
  public Links links;

}

class Links {
  public Link self;
}

class Link {
  public String href;
}
