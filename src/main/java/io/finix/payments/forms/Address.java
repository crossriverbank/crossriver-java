package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

@Builder
public class Address implements Form {

  public String line1;
  public String line2;
  public String city;
  public String region;
  public String postalCode;
  public String country;

}
