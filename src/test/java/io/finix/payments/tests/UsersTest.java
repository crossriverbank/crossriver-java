package io.finix.payments.tests;

import io.finix.payments.ApiClient;
import io.finix.payments.ProcessingTest;
import io.finix.payments.enums.UserRole;
import io.finix.payments.forms.UserCreateForm;
import io.finix.payments.interfaces.ApiError;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.lib.JsonMapper;
import io.finix.payments.views.User;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class UsersTest extends ProcessingTest {

  @Test
  public void test() throws IOException {

    User admin = createUser(unauthenticatedApi, UserRole.ROLE_ADMIN);
    Assert.assertNotNull(admin.id);
    Assert.assertNotNull(admin.password);
    Assert.assertEquals(UserRole.ROLE_ADMIN, admin.role);

    User partner = createUser(unauthenticatedApi, UserRole.ROLE_PARTNER);
    Assert.assertNotNull(partner.id);
    Assert.assertNotNull(partner.password);
    Assert.assertEquals(UserRole.ROLE_PARTNER, partner.role);

    ApiClient partnerApi = ApiClient.builder().url(url).user(partner.id).password(partner.password).build();

    ApiError error1 = unauthenticatedApi.users.id(partner.id).get().error();
    echo(error1);
    ApiError error2 = partnerApi.users.id(partner.id).get().error();
    echo(error2);

    Integer unauthorized = 401;
    Integer notFound = 404;

    Assert.assertEquals(unauthorized, error1.getCode());
    Assert.assertEquals(notFound, error2.getCode());

    ApiClient adminApi = ApiClient.builder().url(url).user(admin.id).password(admin.password).build();

    User user = adminApi.users.id(partner.id).get().view();
    echo(user);
    Assert.assertEquals(partner.role, user.role);

  }

  public static User createUser(ApiClient api, UserRole role) {

    UserCreateForm form = UserCreateForm.builder().role(role).build();
    Maybe<User> response = api.users.post(form);
    if (response.succeeded()) {
      User user = response.view();
      echo(user);
      return user;
    }
    echo(response.error());
    throw new RuntimeException("API error attempting to create user");
  }

}
