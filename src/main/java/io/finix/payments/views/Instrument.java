package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import io.finix.payments.interfaces.View;

import java.util.Currency;
import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "instrument_type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = PaymentCard.class, name = "PAYMENT_CARD"),
  @JsonSubTypes.Type(value = BankAccount.class, name = "BANK_ACCOUNT"),
  @JsonSubTypes.Type(value = VirtualInstrument.class, name = "VIRTUAL")
})
public abstract class Instrument implements View {

  public String id;
  public String name;
  public String fingerprint;
  public String createdAt;
  public String updatedAt;
  public String application;
  public String identity;
  public String type;
  public String payloadType;
  public Currency currency;
  public Map<String,String> tags;

  @JsonTypeId
  public String instrumentType;

}
