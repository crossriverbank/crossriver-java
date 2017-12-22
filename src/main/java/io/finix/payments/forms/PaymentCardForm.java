package io.finix.payments.forms;

import io.finix.payments.enums.InstrumentType;
import lombok.Builder;
import lombok.Getter;

import java.util.Currency;
import java.util.Map;

@Getter
@Builder
public class PaymentCardForm extends InstrumentForm {

  public Integer expirationMonth;
  public Integer expirationYear;
  public String number;
  public String securityCode;
  public Address address;
  public Map additions;
  @Builder.Default
  String type = InstrumentType.PAYMENT_CARD;

  String name;
  String identity;
  String applicationId;
  Currency currency;
  Map<String, String> tags;

}
