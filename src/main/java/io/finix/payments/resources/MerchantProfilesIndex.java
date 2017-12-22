package io.finix.payments.resources;

import io.finix.payments.forms.MerchantProfileForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;

import io.finix.payments.lib.Page;
import io.finix.payments.views.MerchantProfile;


public class MerchantProfilesIndex extends Index<MerchantProfile, Page<MerchantProfile>> {

  public MerchantProfilesIndex(HttpClient client, String path) {
    super(client, path);
  }

  public MerchantProfilesIndex(HttpClient client, String path, Resource scope) {
    super(client, path, scope);
  }

  public Class<MerchantProfile> getViewType() {
    return MerchantProfile.class;
  }

  public Maybe<MerchantProfile> post(MerchantProfileForm form) {
    return super.post(form);
  }

  public Maybe<Page<MerchantProfile>> get() {
    return (Maybe<Page<MerchantProfile>>)(Maybe<?>) super._get();
  }

  public MerchantProfileResource id(String id) {
    return new MerchantProfileResource(getHttpClient(), getPath(), id);
  }

}
