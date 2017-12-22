package io.finix.payments.resources;

import io.finix.payments.forms.RiskProfileForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;

import io.finix.payments.lib.Page;
import io.finix.payments.views.RiskProfile;


public class RiskProfilesIndex extends Index<RiskProfile, Page<RiskProfile>> {

  public RiskProfilesIndex(HttpClient client, String path) {
    super(client, path);
  }

  public RiskProfilesIndex(HttpClient client, String path, Resource scope) {
    super(client, path, scope);
  }

  public Class<RiskProfile> getViewType() {
    return RiskProfile.class;
  }

  public Maybe<RiskProfile> post(RiskProfileForm form) {
    return super.post(form);
  }

  public Maybe<Page<RiskProfile>> get() {
    return (Maybe<Page<RiskProfile>>)(Maybe<?>) super._get();
  }

  public RiskProfileResource id(String id) {
    return new RiskProfileResource(getHttpClient(), getPath(), id);
  }

}
