package io.finix.payments.resources;

import io.finix.payments.forms.SettlementForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.lib.Page;
import io.finix.payments.views.Settlement;

public class SettlementsIndex extends Index<Settlement, Page<Settlement>> {

  public SettlementsIndex(HttpClient client, String path) {
    super(client, path);
  }

  public SettlementsIndex(HttpClient client, String path, Resource scope) {
    super(client, path, scope);
  }

  public Class<Settlement> getViewType() {
    return Settlement.class;
  }

  public Maybe<Settlement> post(SettlementForm form) {
    return (Maybe<Settlement>)(Maybe<?>) super.post(form, Settlement.class);
  }

  public Maybe<Page<Settlement>> get() {
    return (Maybe<Page<Settlement>>)(Maybe<?>) super._get();
  }

  public SettlementResource id(String id) {
    return new SettlementResource(getHttpClient(), getPath(), id);
  }

}
