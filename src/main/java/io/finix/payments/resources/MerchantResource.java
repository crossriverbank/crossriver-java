package io.finix.payments.resources;

import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.views.Merchant;

public class MerchantResource extends Resource<Merchant> {

  public MerchantResource(HttpClient client, String path, String id) {
    super(client, path, id);
  }

  public Class<Merchant> getViewType() {
    return Merchant.class;
  }

  public Maybe<Merchant> get() {
    return super.get();
  }

}
