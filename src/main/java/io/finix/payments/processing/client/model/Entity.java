package io.finix.payments.processing.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Entity {

  private String title;
  private String firstName;
  private String lastName;
  private String email;
  private String businessName;
  private BusinessType businessType;
  private String doingBusinessAs;
  private String phone;
  private String businessPhone;
  private String taxId;
  private String businessTaxId;
  private Address personalAddress;
  private Address businessAddress;
  private Integer mcc;
  private Date dob;
  private Long maxTransactionAmount;
  private String settlementCurrency;
  private String amexMID;
  private String discoverMID;
  private BankAccountType settlementBankAccount;
  private String url;
  private long annualCardVolume;
  private boolean hasAcceptedCreditCardsPreviously = false;
  private String defaultStatementDescriptor;
  private String shortBusinessName;
  private Date incorporationDate;
  private Integer principalPercentageOwnership;
  private String ownershipType;
}
