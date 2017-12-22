package io.finix.payments.tests;

import com.google.common.collect.ImmutableMap;
import io.finix.payments.ApiClient;
import io.finix.payments.ProcessingTest;
import io.finix.payments.enums.UserRole;
import io.finix.payments.forms.*;
import io.finix.payments.forms.Address;
import io.finix.payments.interfaces.ApiError;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.lib.Page;
import io.finix.payments.views.*;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;

import java.util.Currency;

public class VerificationsTest extends ProcessingTest {

  @Test
  public void testVerifyCard() {
    User platform = UsersTest.createUser(unauthenticatedApi, UserRole.ROLE_PLATFORM);
    User partner = UsersTest.createUser(unauthenticatedApi, UserRole.ROLE_PARTNER);
    ApiClient platformApi = ApiClient.builder()
      .url(url)
      .user(platform.id)
      .password(platform.password)
      .build();
    Application application = ApplicationsTest.createApplication(platformApi, partner);
    ApplicationsTest.enableProcessing(platformApi, application);
    ApplicationsTest.registerPaymentProcessor(platformApi, application, "VISA_V1");
    ApiClient api = ApiClient.builder().user(partner.id).password(partner.password).url(url).build();
    Identity identity = MerchantsTest.provisionIdentityWithBankAccount(api);
    PaymentCard card = createCard(api, identity);
    Merchant merchant = MerchantsTest.createMerchant(api, identity, "VISA_V1");
    createVerification(api, card);

    TransferForm form = TransferForm.builder()
      .currency(Currency.getInstance("USD"))
      .amount(1000L)
      .destination(card.id)
      .tags(ImmutableMap.of("order_number", "21DFASJSAKAS"))
      .build();

    // Push to Card
    Transfer xfer = api.transfers.post(form).view();
    echo(xfer);


    // Test idempotency
    for (int i = 0; i < 3; i++)
      api.transfers.post(form);

    String idempotencyId = UUID.randomUUID().toString();

    Maybe<Page<Transfer>> response0 = api.transfers.query()
      .idempotencyId().eq(idempotencyId).get();

    TransferForm idempotentForm = TransferForm.builder()
      .idempotencyId(idempotencyId)
      .currency(Currency.getInstance("USD"))
      .amount(1000L)
      .destination(card.id)
      .tags(ImmutableMap.of("order_number", "21DFASJSAKAS"))
      .build();

    Transfer transfer = api.transfers.post(idempotentForm).view();
    Maybe<Page<Transfer>> response = api.transfers.query()
      .idempotencyId().eq(idempotencyId).get();
    if (response.error() != null) {
      throw new RuntimeException(response.error().getMessage());
    }
    Assert.assertEquals(1, response.view().contents.size());
    Transfer transfer1 = response.view().contents.get(0);
    Assert.assertEquals(transfer.id, transfer1.id);
    Assert.assertEquals(idempotencyId, transfer1.idempotencyId);

    ApiError error = api.transfers.post(idempotentForm).error();
    Assert.assertEquals(Integer.valueOf(422), error.getCode());
  }

  public static PaymentCard createCard(ApiClient api, Identity merchantIdentity) {
    PaymentCardForm form = PaymentCardForm.builder()
      .identity(merchantIdentity.id)
      .name("Bob Serna")
      .number("4957030420210454")
      .securityCode("112")
      .expirationYear(2020)
      .expirationMonth(12)
      .address(
        Address.builder()
          .city("San Mateo")
          .country("USA")
          .region("CA")
          .line1("123 Fake St")
          .line2("#7")
          .postalCode("90210")
          .build()
      )
      .tags(ImmutableMap.of("card_name", "Business Card"))
      .build();
    PaymentCard card = api.instruments.post(form).view();
    echo(card);
    return card;
  }

  public static Verification createVerification(ApiClient api, PaymentCard card) {
    VerificationForm form = VerificationForm.builder()
      .processor("VISA_V1")
      .build();
    Maybe<Verification> response = api.instruments.id(card.id).verifications.post(form);
    if (response.succeeded()) {
      Verification verification = response.view();
      echo(verification);
      return verification;
    }
    echo(response.error());
    throw new RuntimeException("Failed to create card verification");
  }
}
