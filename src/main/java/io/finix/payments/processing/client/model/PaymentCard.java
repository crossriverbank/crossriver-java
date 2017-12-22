package io.finix.payments.processing.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentCard extends PaymentInstrument {

  private String lastFour;
  private int expirationMonth;
  private int expirationYear;
  private String number;
  private String securityCode;
  private Address address;
  private String brand;
  private String cardType;
  private String token;

  public PaymentCard() {
    this.setType(PaymentInstrumentType.PAYMENT_CARD);
  }

  @Builder
  public PaymentCard(String identity, Name name, String lastFour, int expirationMonth, int expirationYear, String number,
                     String securityCode, Address address, String token) {
    this();
    this.lastFour        = lastFour;
    this.expirationMonth = expirationMonth;
    this.expirationYear  = expirationYear;
    this.number          = number;
    this.securityCode    = securityCode;
    this.address         = address;
    this.setName(name);
    this.setIdentity(identity);
    this.setToken(token);
  }
}
