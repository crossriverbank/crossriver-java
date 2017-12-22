package io.finix.payments.resources;

import io.finix.payments.forms.UserCreateForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.lib.Page;
import io.finix.payments.views.User;

public class UsersIndex extends Index<User, Page<User>> {

  public Class<User> getViewType() {
    return User.class;
  }

  public UsersIndex(HttpClient client, String path) {
    super(client, path);
  }

  public Maybe<User> post(UserCreateForm form) {
    return super.post(form);
  }

  public Maybe<Page<User>> get() {
    return (Maybe<Page<User>>)(Maybe<?>) super._get();
  }

  public UserResource id(String id) {
    return new UserResource(getHttpClient(), getPath(), id);
  }

}
