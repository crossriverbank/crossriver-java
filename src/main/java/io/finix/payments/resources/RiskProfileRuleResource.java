package io.finix.payments.resources;

import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.views.RiskProfileRule;

public class RiskProfileRuleResource extends Resource<RiskProfileRule> {

  public RiskProfileRuleResource(HttpClient client, String path, String id) {
    super(client, path, id);
  }

  public Class<RiskProfileRule> getViewType() {
    return RiskProfileRule.class;
  }

  public Maybe<RiskProfileRule> get() {
    return super.get();
  }

}
