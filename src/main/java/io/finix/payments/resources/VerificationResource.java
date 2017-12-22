package io.finix.payments.resources;

import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.views.Verification;

public class VerificationResource extends Resource<Verification> {

  public VerificationResource(HttpClient client, String path, String id) {
    super(client, path, id);
  }

  public Class<Verification> getViewType() {
    return Verification.class;
  }

  public Maybe<Verification> get() {
    return super.get();
  }

}
