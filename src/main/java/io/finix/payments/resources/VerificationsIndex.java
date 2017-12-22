package io.finix.payments.resources;

import io.finix.payments.forms.VerificationForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.lib.Page;
import io.finix.payments.views.Verification;

public class VerificationsIndex extends Index<Verification, Page<Verification>> {

  public Class<Verification> getViewType() {
    return Verification.class;
  }

  public VerificationsIndex(HttpClient client, String path) {
    super(client, path);
  }

  public VerificationsIndex(HttpClient client, String path, Resource scope) {
    super(client, path, scope);
  }

  public Maybe<Verification> post(VerificationForm form) {
    return super.post(form);
  }

  public Maybe<Page<Verification>> get() {
    return (Maybe<Page<Verification>>)(Maybe<?>) super._get();
  }

  public VerificationResource id(String id) {
    return new VerificationResource(getHttpClient(), getPath(), id);
  }

}
