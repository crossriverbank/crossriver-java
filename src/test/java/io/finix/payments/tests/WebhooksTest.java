package io.finix.payments.tests;

import io.finix.payments.ApiClient;
import io.finix.payments.ProcessingTest;
import io.finix.payments.forms.WebhookForm;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.views.Webhook;
import org.junit.Assert;
import org.junit.Test;

public class WebhooksTest extends ProcessingTest {

  @Test
  public void test() {

    String url = "http://httpbin.com";
    ApiClient api = MerchantsTest.provisionPlatform().apiClient;

    // Create a webhook
    Maybe<Webhook> request = api.webhooks.post(WebhookForm.builder().url(url).build());

    if (! request.succeeded()) {
      echo(request.error());
      throw new RuntimeException("Failed to register webhook");
    }

    Webhook webhook = request.view();
    echo(webhook);

    // Disable a webhook
    Webhook updatedWebhook = api.webhooks
      .id(webhook.id)
      .put(WebhookForm.builder().url(url).enabled(false).build())
      .view();

    echo(updatedWebhook);

    // Fetch a webhook
    Webhook fetchedWebhook = api.webhooks
      .id(webhook.id)
      .get()
      .view();

    echo(fetchedWebhook);

    // Validate webhooks
    Assert.assertEquals(webhook.links, fetchedWebhook.links);
    Assert.assertEquals(fetchedWebhook.enabled, false);

  }

}
