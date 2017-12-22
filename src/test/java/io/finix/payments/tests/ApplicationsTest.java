package io.finix.payments.tests;

import io.finix.payments.ApiClient;
import io.finix.payments.ProcessingTest;
import io.finix.payments.enums.BusinessType;
import io.finix.payments.enums.UserRole;
import io.finix.payments.forms.*;
import io.finix.payments.interfaces.ApiError;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.lib.Page;
import io.finix.payments.views.Application;
import io.finix.payments.views.Processor;
import io.finix.payments.views.User;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class ApplicationsTest extends ProcessingTest {

  @Test
  public void testCreate() throws IOException {
    User platform = UsersTest.createUser(unauthenticatedApi, UserRole.ROLE_PLATFORM);
    User partner = UsersTest.createUser(unauthenticatedApi, UserRole.ROLE_PARTNER);

    ApiClient api = ApiClient.builder()
      .url(url)
      .user(platform.id)
      .password(platform.password)
      .build();

    Application app = createApplication(api, partner);
    Assert.assertNotNull(app.id);

    Maybe<Page<Application>> request = api.applications.get();
    Page<Application> page = request.view();
    Page<Application> page2 = page.getNext();
    Page<Application> page1 = page2.getPrevious();
    Assert.assertEquals(
      page.contents.stream().map(Application::getId).collect(Collectors.toList()),
      page1.contents.stream().map(Application::getId).collect(Collectors.toList()));
    for (Application view : page.contents) {
      System.out.println(view.id);
    }

  }

  @Test
  public void testCreationFailure() {
    String[] details = {
      "INVALID_FIELD entity.max_transaction_amount may not be null",
        "INVALID_FIELD entity.business_tax_id may not be null",
        "INVALID_FIELD entity.business_name may not be null",
        "INVALID_FIELD entity.business_phone may not be null",
        "INVALID_FIELD entity.business_type may not be null",
        "INVALID_FIELD entity.doing_business_as may not be null",
        "INVALID_FIELD entity.business_address may not be null"
    };
    Set<String> errors = new HashSet<>(Arrays.asList(details));
    ApiError error = attemptCreateApplication();
    echo(error);
    String[] actualErrors = error.getDetails().split("; ");
    Assert.assertEquals(errors, new HashSet<>(Arrays.asList(actualErrors)));

  }

  public ApiError attemptCreateApplication() {
    User user = new UsersTest().createUser(unauthenticatedApi, UserRole.ROLE_PLATFORM);
    ApiClient api = ApiClient.builder()
      .url(url)
      .user(user.id)
      .password(user.password)
      .build();
    ApplicationForm form = ApplicationForm.builder().build();
    Maybe<Application> result = api.applications.post(form);
    ApiError error = result.error();
    echo(error);
    return error;
  }

  public static Application createApplication(ApiClient api, User partner) {

    ApplicationForm form = ApplicationForm.builder()
//      .maxTransactionAmount(maxTransactionAmount)
      .user(partner.id)
      .entity(
        ApplicationOwnerIdentityEntityForm.builder()
          .maxTransactionAmount(1000L)
          .businessAddress(
            Address.builder()
              .line1("123 1")
              .city("Los Frisco")
              .region("CA")
              .postalCode("90212")
              .country("USA")
              .build()
          )
          .businessName("Asdf Checking")
          .businessPhone("555-555-1234")
          .businessTaxId("123-12-1234")
          .businessType(BusinessType.INDIVIDUAL_SOLE_PROPRIETORSHIP)
          .doingBusinessAs("Asdfasdf")
          .build()
      ).build();
    Maybe<Application> response = api.applications.post(form);
    if (! response.succeeded()) {
      echo(response.error());
      throw new RuntimeException(response.error().toString());
    }
    Application application = response.view();
    echo(application);
    return application;
  }

  public static Processor registerPaymentProcessor(ApiClient api, Application application, String processorName) {
    ProcessorForm form = ProcessorForm.builder()
      .type(processorName)
      .build();
    Maybe<Processor> response = api.applications.id(application.id).processors.post(form);
    if (response.succeeded()) {
      Processor processor = response.view();
      echo(processor);
      return processor;
    }
    echo(response.error());
    throw new RuntimeException(response.error().toString());
  }

  public static Application enableProcessing(ApiClient api, Application application) {
    ApplicationUpdateForm form = ApplicationUpdateForm.builder()
      .processingEnabled(true)
      .settlementEnabled(true)
      .build();
    Maybe<Application> response = api.applications.id(application.id).put(form);
    if (response.succeeded()) {
      Application updatedApplication = response.view();
      echo(updatedApplication);
      return updatedApplication;
    }
    echo(response.error());
    throw new RuntimeException("Failed to enabled processing for application " + application.id);
  }

}
