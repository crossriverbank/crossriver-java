package io.finix.payments.processing.client.exception;

import lombok.Getter;

@Getter
public class ProcessingClientException extends RuntimeException {

  private AbstractErrorResponse error;

  public ProcessingClientException(Throwable cause) {
    super(cause);
  }

  public ProcessingClientException(String s) {
    super(s);
  }

  public ProcessingClientException(String s, AbstractErrorResponse error) {
    this(s);
    this.error = error;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (this.error instanceof EmbeddedErrorResponse) {
      EmbeddedErrorResponse embeddedError = (EmbeddedErrorResponse) this.error;
      embeddedError.getEmbedded().getErrors().forEach(
        err -> sb.append(err.getCode())
          .append(": ")
          .append(err.getMessage())
          .append(" (log ref ")
          .append(err.getLogref())
          .append(")")
          .append(System.lineSeparator())
      );
    }
    else {
      if (this.error != null) {
        sb.append(this.error.toString());
      }
      else {
        sb.append(this.getMessage());
      }
    }
    return sb.toString();
  }
}
