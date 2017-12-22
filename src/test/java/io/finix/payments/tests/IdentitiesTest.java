package io.finix.payments.tests;

import io.finix.payments.ApiClient;
import io.finix.payments.ProcessingTest;
import io.finix.payments.enums.BusinessType;
import io.finix.payments.enums.UserRole;
import io.finix.payments.forms.Address;
import io.finix.payments.forms.Date;
import io.finix.payments.forms.IdentityEntityForm;
import io.finix.payments.forms.IdentityForm;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.lib.Page;
import io.finix.payments.views.Application;
import io.finix.payments.views.Identity;
import io.finix.payments.views.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class IdentitiesTest extends ProcessingTest {

  @Test
  public void testCreateIdentity() {

    User partner = UsersTest.createUser(unauthenticatedApi, UserRole.ROLE_PARTNER);
    ApiClient api = ApiClient.builder().user(partner.id).password(partner.password).url(url).build();
    Identity identity = IdentitiesTest.createIdentity(api);
  }

  @Test
  public void testUpdateIdentity() {

    ApiClient api = MerchantsTest.provisionPlatform().apiClient;
    Identity identity = IdentitiesTest.createIdentity(api);

    IdentityForm form = IdentityForm.builder()
      .entity(
        IdentityEntityForm.builder()
          .firstName("dwayne")
          .email("self@newdomain.com")
          .businessPhone("+1 (408) 756-4497")
          .build())
      .build();
    Identity identityView = api.identities.id(identity.id).get().view();
    Maybe<Identity> response = api.identities.id(identity.id).put(form);
    if (! response.succeeded()) {
      echo(response.error());
      throw new RuntimeException("API error attempting to create identity");
    }
    Identity updatedIdentity = response.view();
    echo(updatedIdentity);

  }

  @Test
  public void testIdentitiesIndex() {
    MerchantsTest.Platform platform = MerchantsTest.provisionPlatform();
    ApiClient api = platform.apiClient;
    Application application = platform.application;
    List<String> identityIds = new ArrayList<>();
    identityIds.add(application.owner);
    for (int i = 0; i < 61; i++) {
      identityIds.add(IdentitiesTest.createIdentity(api).id);
    }
    List<String> retrievedIdentityIds = new ArrayList<>();
    Page<Identity> page = api.applications.id(application.id).identities.get().view();
    retrievedIdentityIds.addAll(page.contents.stream().map(Identity::getId).collect(Collectors.toList()));
    while (page.hasNext()) {
      page = page.getNext();
      retrievedIdentityIds.addAll(page.contents.stream().map(Identity::getId).collect(Collectors.toList()));
    }
    Assert.assertEquals(new HashSet<>(identityIds), new HashSet<>(retrievedIdentityIds));

    retrievedIdentityIds = new ArrayList<>();
    page = api.identities.get().view();
    retrievedIdentityIds.addAll(page.contents.stream().map(Identity::getId).collect(Collectors.toList()));
    while (page.hasNext()) {
      page = page.getNext();
      retrievedIdentityIds.addAll(page.contents.stream().map(Identity::getId).collect(Collectors.toList()));
    }
    Assert.assertEquals(new HashSet<>(identityIds), new HashSet<>(retrievedIdentityIds));
  }

  public static Identity createIdentity(ApiClient api) {

    IdentityForm form = IdentityForm.builder()
      .entity(
        IdentityEntityForm.builder()
          .firstName("dwayne")
          .lastName("Sunkhronos")
          .email("user@example.org")
          .businessName("business inc")
          .businessType(BusinessType.LIMITED_LIABILITY_COMPANY)
          .doingBusinessAs("doingBusinessAs")
          .phone("1234567890")
          .businessPhone("+1 (408) 756-4497")
          .taxId("123456789")
          .businessTaxId("123456789")
          .personalAddress(
            Address.builder()
              .line1("741 Douglass St")
              .line2("Apartment 7")
              .city("San Mateo")
              .region("CA")
              .postalCode("94114")
              .country("USA")
              .build())
          .businessAddress(
            Address.builder()
              .line1("741 Douglass St")
              .line2("Apartment 7")
              .city("San Mateo")
              .region("CA")
              .postalCode("94114")
              .country("USA")
              .build())
          .dob(Date.builder()
            .day(27)
            .month(5)
            .year(1978)
            .build())
          .maxTransactionAmount(1000L)
          .mcc("7399")
          .url("http://sample-entity.com")
          .annualCardVolume(100L)
          .defaultStatementDescriptor("Business Inc")
          .incorporationDate(Date.builder()
            .day(1)
            .month(12)
            .year(2012)
            .build())
          .principalPercentageOwnership(51)
          .build())
      .build();

    Maybe<Identity> response = api.identities.post(form);

    if (response.succeeded()) {
      Identity identity = response.view();
      echo(identity);
      return identity;
    }

    echo(response.error());
    throw new RuntimeException("API error attempting to create identity");
  }
}
