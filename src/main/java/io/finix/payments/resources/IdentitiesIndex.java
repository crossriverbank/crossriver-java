package io.finix.payments.resources;

import io.finix.payments.forms.IdentityForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.lib.Page;
import io.finix.payments.views.Identity;

public class IdentitiesIndex extends Index<Identity, Page<Identity>> {

  public IdentitiesIndex(HttpClient client, String path) {
    super(client, path);
  }

  public IdentitiesIndex(HttpClient client, String path, Resource scope) {
    super(client, path, scope);
  }

  public Class<Identity> getViewType() {
    return Identity.class;
  }

  public Maybe<Identity> post(IdentityForm form) {
    return super.post(form);
  }

  public Maybe<Page<Identity>> get() {
    return (Maybe<Page<Identity>>)(Maybe<?>) super._get();
  }

  public IdentityResource id(String id) {
    return new IdentityResource(getHttpClient(), getPath(), id);
  }

}
