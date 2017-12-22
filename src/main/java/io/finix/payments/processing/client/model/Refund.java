package io.finix.payments.processing.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Refund extends AbstractModel {
  private Long refundAmount;

  public Refund() {
  }

  @Builder
  public Refund(Long refundAmount) {
    this.refundAmount = refundAmount;
  }
}
