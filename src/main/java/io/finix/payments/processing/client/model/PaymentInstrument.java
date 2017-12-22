package io.finix.payments.processing.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PaymentInstrument extends AbstractModel {
  private Name name;
  private PaymentInstrumentType type;
  private String identity;
}
