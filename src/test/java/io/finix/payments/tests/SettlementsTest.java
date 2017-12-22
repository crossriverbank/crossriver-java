package io.finix.payments.tests;

import io.finix.payments.ApiClient;
import io.finix.payments.ProcessingTest;
import io.finix.payments.forms.SettlementForm;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.views.Authorization;
import io.finix.payments.views.Settlement;
import io.finix.payments.views.Transfer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Currency;


public class SettlementsTest extends ProcessingTest {

  @Test
  public void test() {

    ApiClient api = MerchantsTest.provisionPlatform().apiClient;
    Authorization authorization = TransfersTest.createAuthorization(api);
    Authorization captureAuthorization = TransfersTest.captureAuthorization(api, authorization);
    Transfer transfer = api.transfers.id(captureAuthorization.transfer).get().view();
    echo(transfer);

    SettlementForm form = SettlementForm.builder()
      .currency(Currency.getInstance("USD"))
      .build();

    Maybe<Settlement> response = api
      .identities
      .id(transfer.merchantIdentity)
      .settlements
      .post(form);

    if (! response.succeeded()) {
      Assert.assertEquals("UNPROCESSABLE_ENTITY  There are no unsettled SUCCEEDED transfers to be settled.", response.error().getDetails());
    }
  }

}
