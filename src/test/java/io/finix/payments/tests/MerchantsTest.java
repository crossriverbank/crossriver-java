package io.finix.payments.tests;

import com.google.common.collect.ImmutableMap;
import io.finix.payments.ApiClient;
import io.finix.payments.ProcessingTest;
import io.finix.payments.enums.UserRole;
import io.finix.payments.forms.MerchantUnderwritingForm;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.views.Application;
import io.finix.payments.views.Identity;
import io.finix.payments.views.Merchant;
import io.finix.payments.views.User;
import lombok.Builder;
import org.junit.Test;

public class MerchantsTest extends ProcessingTest {

  @Test
  public void testCreate() {
    Merchant merchant = createMerchant();
  }

  public static Merchant createMerchant() {
    ApiClient api = provisionPlatform().apiClient;
    Identity identity = provisionIdentityWithBankAccount(api);
    return createMerchant(api, identity);
  }

  public static Merchant createMerchant(ApiClient api) {
    Identity identity = provisionIdentityWithBankAccount(api);
    return createMerchant(api, identity);
  }

  public static Merchant createMerchant(ApiClient api, Identity identity) {
    return createMerchant(api, identity, "DUMMY_V1");
  }

  public static Merchant createMerchant(ApiClient api, Identity identity, String processorName) {
    MerchantUnderwritingForm form = MerchantUnderwritingForm.builder()
      .processor(processorName)
      .tags(ImmutableMap.of("key", "value"))
      .build();

    Maybe<Merchant> request = api.identities.id(identity.id).merchants.post(form);

    if (request.succeeded()) {
      Merchant merchant = request.view();
      echo(merchant);
      return merchant;
    }
    echo(request.error());
    throw new RuntimeException("API error attempting to create merchant");
  }

  public static Platform provisionPlatform() {
    return provisionPlatform("DUMMY_V1");
  }

  public static Platform provisionPlatform(String processorName) {
    User platform = UsersTest.createUser(unauthenticatedApi, UserRole.ROLE_PLATFORM);
    User partner = UsersTest.createUser(unauthenticatedApi, UserRole.ROLE_PARTNER);

    ApiClient platformApi = ApiClient.builder()
      .url(url)
      .user(platform.id)
      .password(platform.password)
      .build();

    // Partner needs a default application
    Application application = ApplicationsTest.createApplication(platformApi, partner);
    ApplicationsTest.enableProcessing(platformApi, application);

    // Application needs a default payment processor
    ApplicationsTest.registerPaymentProcessor(platformApi, application, processorName);

    ApiClient api = ApiClient.builder().user(partner.id).password(partner.password).url(url).build();
    return new Platform(api, application, platformApi);
  }


  public static class Platform {
    public Application application;
    public ApiClient apiClient;
    public ApiClient platformApiClient;

    public Platform(ApiClient api, Application application, ApiClient platformApiClient) {
      this.apiClient = api;
      this.application = application;
      this.platformApiClient = platformApiClient;
    }
  }

  public static Identity provisionIdentityWithBankAccount(ApiClient api) {
    Identity identity = IdentitiesTest.createIdentity(api);
    // Identity needs a bank account to be underwritten as a merchant
    InstrumentsTest.createBankAccount(api, identity);
    return identity;
  }

}
