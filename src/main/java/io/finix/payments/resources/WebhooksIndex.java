package io.finix.payments.resources;

import io.finix.payments.forms.WebhookForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.lib.Page;
import io.finix.payments.views.Webhook;

public class WebhooksIndex extends Index<Webhook, Page<Webhook>> {

  public Class<Webhook> getViewType() {
    return Webhook.class;
  }

  public WebhooksIndex(HttpClient client, String path) {
    super(client, path);
  }

  public Maybe<Webhook> post(WebhookForm form) {
    return super.post(form);
  }

  public Maybe<Page<Webhook>> get() {
    return (Maybe<Page<Webhook>>)(Maybe<?>) super._get();
  }

  public WebhookResource id(String id) {
    return new WebhookResource(getHttpClient(), getPath(), id);
  }

}
