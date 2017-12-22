package io.finix.payments.processing.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address {
  private String line1;
  private String line2;
  private String city;
  private String region;
  private String postalCode;
  private String country;

  public Address() {
  }

  @Builder
  public Address(String line1, String line2, String city, String region, String postalCode, String country) {
    this.line1 = line1;
    this.line2 = line2;
    this.city = city;
    this.region = region;
    this.postalCode = postalCode;
    this.country = country;
  }
}
