package io.finix.payments.processing.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class PaymentCardToken extends PaymentInstrument {

  private PaymentInstrumentType type;
  private String token;
  private String identity;
  private Name name;

  public PaymentCardToken(PaymentInstrumentType type, String token, String identity, Name name) {
    this.setType(PaymentInstrumentType.TOKEN);
    this.setToken(token);
    this.setIdentity(identity);
    this.setName(name);
  }
}
