package io.finix.payments.processing.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class Verification extends AbstractModel {
  public enum State {
    PENDING, SUCCEEDED, FAILED
  }

  private String externalTraceId;
  private String processor;
  private State state;
  private String merchant;
  private String identity;
  private String paymentInstrument;

  public Verification() {
  }

  @Builder
  public Verification(String externalTraceId, String processor, State state, String merchant, String identity, String paymentInstrument, Map<String, String> tags) {
    this.externalTraceId = externalTraceId;
    this.processor = processor;
    this.state = state;
    this.merchant = merchant;
    this.identity = identity;
    this.paymentInstrument = paymentInstrument;
    this.setTags(tags);
  }
}
