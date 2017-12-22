package io.finix.payments.resources;

import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.views.RiskProfile;

public class RiskProfileResource extends Resource<RiskProfile> {

  public RiskProfileRulesIndex rules;

  public RiskProfileResource(HttpClient client, String path, String id) {
    super(client, path, id);
    this.rules = new RiskProfileRulesIndex(client, "/rules", this);
  }

  public Class<RiskProfile> getViewType() {
    return RiskProfile.class;
  }

  public Maybe<RiskProfile> get() {
    return super.get();
  }

}
