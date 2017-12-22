package io.finix.payments.resources;

import io.finix.payments.forms.ApplicationForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.lib.HttpClient;

import io.finix.payments.lib.Page;
import io.finix.payments.views.Application;


public class ApplicationsIndex extends Index<Application, Page<Application>> {

  public ApplicationsIndex(HttpClient client, String path) {
    super(client, path);
  }

  public Class<Application> getViewType() {
    return Application.class;
  }

  public Maybe<Application> post(ApplicationForm form) {
    return super.post(form);
  }

  public Maybe<Page<Application>> get() {
    return (Maybe<Page<Application>>)(Maybe<?>) super._get();
  }

  public ApplicationResource id(String id) {
    return new ApplicationResource(getHttpClient(), getPath(), id);
  }

}
