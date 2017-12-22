package io.finix.payments.forms;

import io.finix.payments.enums.BankAccountType;
import io.finix.payments.enums.InstrumentType;
import lombok.Builder;
import lombok.Getter;

import java.util.Currency;
import java.util.Map;

@Getter
@Builder
public class BankAccountForm extends InstrumentForm {

  public String accountNumber;
  public String bankCode;
  public BankAccountType accountType;
  public String iban;
  public String bic;
  public String companyName;
  public String country;

  @Builder.Default
  String type = InstrumentType.BANK_ACCOUNT;

  String name;
  String identity;
  String applicationId;
  Currency currency;
  Map<String, String> tags;

}
