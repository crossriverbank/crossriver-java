package io.finix.payments.tests;

import io.finix.payments.ApiClient;
import io.finix.payments.ProcessingTest;
import io.finix.payments.enums.BankAccountType;
import io.finix.payments.enums.InstrumentType;
import io.finix.payments.enums.UserRole;
import io.finix.payments.forms.BankAccountForm;
import io.finix.payments.forms.PaymentCardForm;
import io.finix.payments.interfaces.ApiError;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.lib.Page;
import io.finix.payments.views.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Currency;

public class InstrumentsTest extends ProcessingTest {

  ApiClient partnerApi;
  Identity identity;

  @Before
  public void setup() {
    User platform = UsersTest.createUser(unauthenticatedApi, UserRole.ROLE_PLATFORM);
    User partner = UsersTest.createUser(unauthenticatedApi, UserRole.ROLE_PARTNER);

    ApiClient platformApi = ApiClient.builder()
      .url(url)
      .user(platform.id)
      .password(platform.password)
      .build();

    // Partner needs a default application
    Application application = ApplicationsTest.createApplication(platformApi, partner);

    partnerApi = ApiClient.builder().user(partner.id).password(partner.password).url(url).build();
    identity = IdentitiesTest.createIdentity(partnerApi);
  }

  @Test
  public void testGetIndex() {
    PaymentCard card = createCard(partnerApi, identity);
    BankAccount bankAccount = createBankAccount(partnerApi, identity);
    Page<Instrument> page = partnerApi.instruments.get().view();
    for (Instrument instrument : page.contents) {
      echo(instrument);
      if (instrument.type.equals(InstrumentType.BANK_ACCOUNT.toString())) {
        BankAccount ba = (BankAccount) instrument;
        System.out.println(ba.bankCode);
      } else if (instrument.type.equals(InstrumentType.PAYMENT_CARD)) {
        PaymentCard pc = (PaymentCard) instrument;
        System.out.println(pc.expirationYear);
      }
    }
    while (page.hasNext()) {
      page = page.getNext();
      echo(page);
    }
  }

  @Test
  public void testCreateCard() {
    PaymentCard card = createCard(partnerApi, identity);

    Maybe<Instrument> maybeInstrument = partnerApi.instruments.id(card.id).get();
    Instrument instrument = maybeInstrument.view();
    echo(instrument);

    PaymentCard cardInstrument = (PaymentCard) instrument;
    echo(instrument);

    Maybe<PaymentCard> maybeCard = partnerApi.instruments.id(card.id).get();
    PaymentCard cardView = maybeCard.view();
    echo(cardView);

  }

  @Test
  public void testCreateBankAccount() {
    BankAccount bankAccount = createBankAccount(partnerApi, identity);

    Maybe<Instrument> maybeInstrument = partnerApi.instruments.id(bankAccount.id).get();
    Instrument instrument = maybeInstrument.view();
    echo(instrument);

    BankAccount bankAccountInstrument = (BankAccount) instrument;
    echo(instrument);

    Maybe<BankAccount> maybeAcct = partnerApi.instruments.id(bankAccount.id).get();
    BankAccount acctView = maybeAcct.view();
    echo(acctView);

  }

  public static PaymentCard createCard(ApiClient api, Identity identity) {

    PaymentCardForm cardForm = PaymentCardForm.builder()
      .name("Joe Doe")
      .identity(identity.id)
      .expirationMonth(12)
      .expirationYear(2030)
      .number("4111 1111 1111 1111")
      .securityCode("231")
      .build();

    Maybe<PaymentCard> maybeCard = api.instruments.post(cardForm);

    if (maybeCard.succeeded()) {
      PaymentCard card = maybeCard.view();
      echo(card);
      return card;
    }
    ApiError error = maybeCard.error();
    echo(error);
    throw new RuntimeException("API error attempting to create payment card");
  }

  public static BankAccount createBankAccount(ApiClient api, Identity identity) {

    BankAccountForm form = BankAccountForm.builder()
      .name("Joe Doe")
      .identity(identity.id)
      .accountNumber("84012312415")
      .bankCode("840123124")
      .accountType(BankAccountType.SAVINGS)
      .companyName("company name")
      .country("USA")
      .currency(Currency.getInstance("USD"))
      .build();

    Maybe<BankAccount> request = api.instruments.post(form);

    if (request.succeeded()) {
      BankAccount bankAccount = request.view();
      echo(bankAccount);
      return bankAccount;
    }

    ApiError error = request.error();
    echo(error);
    throw new RuntimeException("API error attempting to create bank account");
  }

}
