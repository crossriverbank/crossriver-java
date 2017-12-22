package io.finix.payments.processing.client.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

/*
sample {
  "total": 15,
  "_embedded": {
    "errors": [
      {
        "logref": "be86a3d9-2c7a-4e2a-83fc-08ba7c838d7b",
        "message": "entity.settlement_bank_account, may not be null",
        "code": "INVALID_FIELD",
        "context": null,
        "_links": {
          "self": {
            "href": "http://b-processing-staging-01-9kcywb6umx.elasticbeanstalk.com/applications/"
          }
        }
      }
    ]
  }
}
 * */
@Getter
public class EmbeddedErrorResponse extends AbstractErrorResponse {
  private int total;

  @JsonProperty("_embedded")
  private Embedded embedded;

  @Getter
  public static class Embedded {
    private List<Error> errors;
  }

  @Getter
  public static class Error {
    private String logref;
    private String message;
    private String code;
  }
}
