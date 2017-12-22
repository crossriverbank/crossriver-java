package io.finix.payments.forms;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class CardTokenForm extends TokenForm {

  @Builder.Default
  public String type = "PAYMENT_CARD";

  public Map<String, String> tags;
  private String name;
  private String lastFour;
  private Integer expirationMonth;
  private Integer expirationYear;
  private String number;
  private String securityCode;
  private Address address;
  private String brand;
  private String cardType;

}
