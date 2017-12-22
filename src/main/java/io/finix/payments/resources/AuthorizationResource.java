package io.finix.payments.resources;

import io.finix.payments.forms.AuthorizationUpdateForm;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.views.Authorization;

public class AuthorizationResource extends Resource<Authorization> {

  public AuthorizationResource
    (HttpClient client, String path, String id) {
    super(client, path, id);
  }

  public Class<Authorization> getViewType() {
    return Authorization.class;
  }

  public Maybe<Authorization> get() {
    return super.get();
  }

  public Maybe<Authorization> put(AuthorizationUpdateForm form) {
    return super.put(form);
  }

}
