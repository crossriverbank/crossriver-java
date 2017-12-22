package io.finix.payments.resources;

import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.views.MerchantProfile;

public class MerchantProfileResource extends Resource<MerchantProfile> {

  public MerchantProfileResource(HttpClient client, String path, String id) {
    super(client, path, id);
  }

  public Class<MerchantProfile> getViewType() {
    return MerchantProfile.class;
  }

  public Maybe<MerchantProfile> get() {
    return super.get();
  }

}
