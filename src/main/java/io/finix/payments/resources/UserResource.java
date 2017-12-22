package io.finix.payments.resources;

import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.views.User;

public class UserResource extends Resource<User> {

  public UserResource(HttpClient client, String path, String id) {
    super(client, path, id);
  }

  public Class<User> getViewType() {
    return User.class;
  }

  public Maybe<User> get() {
    return super.get();
  }

}
