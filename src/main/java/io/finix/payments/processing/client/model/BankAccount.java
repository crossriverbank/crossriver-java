package io.finix.payments.processing.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BankAccount extends PaymentInstrument {
  private String accountNumber;
  private String bankCode;
  private BankAccountType accountType;
  private String companyName;
  private String country;
  private String currency;
  private String maskedAccountNumber;

  public BankAccount() {
    this.setType(PaymentInstrumentType.BANK_ACCOUNT);
  }

  @Builder
  public BankAccount(String identity, Name name, String accountNumber, String bankCode, BankAccountType accountType,
                     String companyName, String country, String currency) {
    this();
    this.accountNumber = accountNumber;
    this.bankCode = bankCode;
    this.accountType = accountType;
    this.companyName = companyName;
    this.country = country;
    this.currency = currency;
    this.setName(name);
    this.setIdentity(identity);
  }
}
