package io.finix.payments.processing.client.model;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Authorization extends AbstractModel {

  public enum State {SUCCEEDED, FAILED, UNKNOWN}

  private Long amount;
  private Boolean voidMe;
  private State state;
  private String traceId;
  private String currency;
  private String transfer;
  private String source;
  private String processor;
  private String merchantIdentity;
  private Long captureAmount;
  private Long fee;
  private String statementDescriptor;

  public Authorization() {
  }

  @Builder
  private Authorization(Long amount, Boolean voidMe, String currency, String source, String processor, String merchantIdentity, Long captureAmount, Long fee, Map<String, String> tags, String transfer) {
    this.amount = amount;
    this.voidMe = voidMe;
    this.currency = currency;
    this.source = source;
    this.processor = processor;
    this.merchantIdentity = merchantIdentity;
    this.captureAmount = captureAmount;
    this.tags = tags;
    this.fee = fee;
    this.transfer = transfer;
  }

  public Authorization capture() {
    return capture(amount);
  }

  public Authorization capture(Long captureAmount) {
    return capture(captureAmount, 0L);
  }

  public Authorization capture(Long captureAmount, Long fee) {
    return client.authorizationsClient().save(getId(), Authorization.builder()
      .captureAmount(captureAmount)
      .fee(fee)
      .build());
  }

  public Authorization voidMe(boolean voidMe) {
    return client.authorizationsClient().save(getId(), Authorization.builder()
      .voidMe(voidMe)
      .build());
  }
}
