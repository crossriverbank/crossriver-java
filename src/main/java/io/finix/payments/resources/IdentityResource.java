package io.finix.payments.resources;

import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.views.Identity;

public class IdentityResource extends Resource<Identity> {

  public MerchantsIndex merchants;

  public SettlementsIndex settlements;

  public IdentityResource(HttpClient client, String path, String id) {
    super(client, path, id);
    this.merchants = new MerchantsIndex(client, "/merchants", this);
    this.settlements = new SettlementsIndex(client, "/settlements", this);
  }

  public Class<Identity> getViewType() {
    return Identity.class;
  }

  public Maybe<Identity> get() {
    return super.get();
  }

}
