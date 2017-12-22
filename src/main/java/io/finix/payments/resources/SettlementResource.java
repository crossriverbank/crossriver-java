package io.finix.payments.resources;

import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.views.Settlement;

public class SettlementResource extends Resource<Settlement> {

  public SettlementResource(HttpClient client, String path, String id) {
    super(client, path, id);
  }

  public Class<Settlement> getViewType() {
    return Settlement.class;
  }

  public Maybe<Settlement> get() {
    return super.get();
  }

}
