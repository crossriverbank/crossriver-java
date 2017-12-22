package io.finix.payments.resources;

import io.finix.payments.forms.MerchantUnderwritingForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.lib.Page;
import io.finix.payments.views.Merchant;

public class MerchantsIndex extends Index<Merchant, Page<Merchant>> {

  public MerchantsIndex(HttpClient client, String path) {
    super(client, path);
  }

  public MerchantsIndex(HttpClient client, String path, Resource scope) {
    super(client, path, scope);
  }

  public Class<Merchant> getViewType() {
    return Merchant.class;
  }

  public Maybe<Merchant> post(MerchantUnderwritingForm form) {
    return (Maybe<Merchant>)(Maybe<?>) super.post(form, Merchant.class);
  }

  public Maybe<Page<Merchant>> get() {
    return (Maybe<Page<Merchant>>)(Maybe<?>) super._get();
  }

  public MerchantResource id(String id) {
    return new MerchantResource(getHttpClient(), getPath(), id);
  }

}
