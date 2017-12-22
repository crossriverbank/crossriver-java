package io.finix.payments.views;

import io.finix.payments.interfaces.View;
import lombok.Getter;

@Getter
public class Address implements View {

  public String line1;
  public String line2;
  public String city;
  public String region;
  public String postalCode;
  public String country;

}
