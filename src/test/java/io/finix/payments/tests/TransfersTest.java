package io.finix.payments.tests;

import io.finix.payments.ApiClient;
import io.finix.payments.ProcessingTest;
import io.finix.payments.forms.AuthorizationCreateForm;
import io.finix.payments.forms.AuthorizationUpdateForm;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.lib.Page;
import io.finix.payments.views.*;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;

public class TransfersTest extends ProcessingTest {

  @Test
  public void test() {
    ApiClient api = MerchantsTest.provisionPlatform().apiClient;
    Authorization authorization = createAuthorization(api);
    Authorization captureAuthorization = captureAuthorization(api, authorization);
    Transfer transfer = api.transfers.id(captureAuthorization.transfer).get().view();
    echo(transfer);
  }

  @Test
  public void testAuthsQueryByIdempotencyId() {
    ApiClient api = MerchantsTest.provisionPlatform().apiClient;
    // Create some other auths
    for (int i = 0; i < 3; i ++)
      createAuthorization(api);
    String idempotencyId = UUID.randomUUID().toString();
    Authorization auth = createAuthorization(api, idempotencyId);
    Maybe<Page<Authorization>> response = api.authorizations.query()
      .idempotencyId().eq(idempotencyId).get();
    if (response.error() != null) {
      throw new RuntimeException(response.error().getMessage());
    }
    Assert.assertEquals(1, response.view().contents.size());
    Authorization authorization = response.view().contents.get(0);
    Assert.assertEquals(auth.id, authorization.id);
    Assert.assertEquals(idempotencyId, authorization.idempotencyId);
  }

  public static Authorization createAuthorization(ApiClient api) {
    return createAuthorization(api, UUID.randomUUID().toString());
  }

  public static Authorization createAuthorization(ApiClient api, String idempotencyId) {

    Merchant merchant = MerchantsTest.createMerchant(api);

    Identity buyer = IdentitiesTest.createIdentity(api);
    PaymentCard card = InstrumentsTest.createCard(api, buyer);

    AuthorizationCreateForm form = AuthorizationCreateForm.builder()
      .amount(100L)
      .merchantIdentity(merchant.identity)
      .source(card.id)
      .idempotencyId(idempotencyId)
      .build();

    Maybe<Authorization> response = api.authorizations.post(form);

    if (response.succeeded()) {
      Authorization authorization = response.view();
      echo(authorization);
      return authorization;
    }

    echo(response.error());
    throw new RuntimeException("API error attempting to create auth");
  }

  public static Authorization captureAuthorization(ApiClient api, Authorization authorization) {

    AuthorizationUpdateForm form = AuthorizationUpdateForm.builder()
      .captureAmount(authorization.amount)
      .fee(10L)
      .statementDescriptor("Order 123")
      .build();
    Maybe<Authorization> response = api.authorizations.id(authorization.id).put(form);

    if (response.succeeded()) {
      Authorization capturedAuth = response.view();
      echo(capturedAuth);
      return capturedAuth;
    }

    echo(response.error());
    throw new RuntimeException("API error attempting to capture auth");
  }

}
