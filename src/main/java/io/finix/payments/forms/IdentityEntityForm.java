package io.finix.payments.forms;

import io.finix.payments.enums.BusinessType;
import io.finix.payments.enums.OwnershipType;
import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Currency;

@Builder
public class IdentityEntityForm implements Form {

  public String title;
  public String firstName;
  public String lastName;
  public String email;
  public String businessName;
  public BusinessType businessType;
  public String doingBusinessAs;
  public String phone;
  public String businessPhone;
  public String taxId;
  public String businessTaxId;
  public Address personalAddress;
  public Address businessAddress;
  public String mcc;
  public Date dob;
  public String amexMID;
  public String discoverMID;
  public Long maxTransactionAmount;
  public Boolean hasAcceptedCreditCardsPreviously;
  public String url;
  public Long annualCardVolume;
  public String defaultStatementDescriptor;
  public String shortBusinessName;
  public Date incorporationDate;
  public Integer principalPercentageOwnership;
  public OwnershipType ownershipType;

}
