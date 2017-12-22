package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.enums.CardAddressVerification;
import io.finix.payments.enums.CardBrand;
import io.finix.payments.enums.CardSecurityCode;
import io.finix.payments.enums.CardType;
import lombok.Getter;

import java.util.Map;

@Getter
public class PaymentCard extends Instrument {

  public Integer expirationMonth;
  public Integer expirationYear;
  public String lastFour;
  public CardBrand brand;
  public CardType cardType;
  public String name;
  public Address address;
  public CardAddressVerification addressVerification;
  public CardSecurityCode securityCodeVerification;

  public String bin;
  public String onlineGambingBlockIndicator;
  public String pushFundsBlockIndicator;
  public String fastFundsIndicator;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
