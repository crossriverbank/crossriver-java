package io.finix.payments.resources;

import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.views.Webhook;

public class WebhookResource extends Resource<Webhook> {

  public WebhookResource(HttpClient client, String path, String id) {
    super(client, path, id);
  }

  public Class<Webhook> getViewType() {
    return Webhook.class;
  }

  public Maybe<Webhook> get() {
    return super.get();
  }

}
