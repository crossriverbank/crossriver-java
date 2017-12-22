package io.finix.payments.resources;

import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.views.Transfer;


public class TransferResource extends Resource<Transfer> {

  public TransferResource(HttpClient client, String path, String id) {
    super(client, path, id);
  }

  public Class<Transfer> getViewType() {
    return Transfer.class;
  }

  public Maybe<Transfer> get() {
    return super.get();
  }

}
