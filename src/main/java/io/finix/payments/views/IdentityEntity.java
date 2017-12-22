package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.enums.BusinessType;
import io.finix.payments.enums.OwnershipType;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.Map;

@Getter
public class IdentityEntity implements View {

  public String title;
  public String firstName;
  public String lastName;
  public String email;
  public String businessName;
  public BusinessType businessType;
  public String doingBusinessAs;
  public String phone;
  public String businessPhone;
  public Address personalAddress;
  public Address businessAddress;
  public String mcc;
  public Date dob;
  public Long maxTransactionAmount;
  public String amexMID;
  public String discoverMID;
  public String url;
  public Long annualCardVolume;
  public Boolean hasAcceptedCreditCardsPreviously;
  public Date incorporationDate;
  public Integer principalPercentageOwnership;
  public Boolean taxIdProvided;
  public Boolean businessTaxIdProvided;
  public String defaultStatementDescriptor;
  public String shortBusinessName;
  public OwnershipType ownershipType;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
