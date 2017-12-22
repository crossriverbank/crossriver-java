package io.finix.payments.tests;

import com.google.common.collect.ImmutableMap;
import io.finix.payments.ApiClient;
import io.finix.payments.ProcessingTest;
import io.finix.payments.enums.UserRole;
import io.finix.payments.forms.CardTokenForm;
import io.finix.payments.forms.TokenAssociationForm;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.views.*;

import org.junit.Test;

public class TokensTest extends ProcessingTest {

  @Test
  public void test() {
    User platform = UsersTest.createUser(unauthenticatedApi, UserRole.ROLE_PLATFORM);
    echo(platform);
    User partner = UsersTest.createUser(unauthenticatedApi, UserRole.ROLE_PARTNER);
    echo(partner);
    ApiClient platformApi = ApiClient.builder()
      .url(url)
      .user(platform.id)
      .password(platform.password)
      .build();

    // Partner needs a default application
    Application application = ApplicationsTest.createApplication(platformApi, partner);
    echo(application);

    CardTokenForm tokenForm = CardTokenForm.builder()
      .name("Foobar Barfoo")
      .expirationMonth(12)
      .expirationYear(2030)
      .number("4111111111111111")
      .securityCode("231")
      .tags(ImmutableMap.of("order", "E3X9R"))
      .build();

    Maybe<InstrumentToken> response = unauthenticatedApi.applications.id(application.id).tokens.post(tokenForm);

    if (! response.succeeded()) {
      echo(response.error());
      throw new RuntimeException("Failed to create token");
    }

    InstrumentToken token = response.view();
    echo(token);

    ApiClient api = ApiClient.builder()
      .url(url)
      .user(partner.id)
      .password(partner.password)
      .build();

    Identity identity = IdentitiesTest.createIdentity(api);
    echo(identity);

    Maybe<PaymentCard> cardResponse = api.instruments.post(TokenAssociationForm.builder()
      .identity(identity.id)
      .token(token.id)
      .build());

    if (! cardResponse.succeeded()) {
      echo(cardResponse.error());
      throw new RuntimeException("Failed to create token");
    }
    PaymentCard card = cardResponse.view();
    echo(card);

  }
}
