package io.finix.payments.resources;

import io.finix.payments.forms.RiskProfileForm;
import io.finix.payments.forms.RiskProfileRuleForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;

import io.finix.payments.lib.Page;
import io.finix.payments.views.RiskProfileRule;


public class RiskProfileRulesIndex extends Index<RiskProfileRule, Page<RiskProfileRule>> {

  public RiskProfileRulesIndex(HttpClient client, String path) {
    super(client, path);
  }

  public RiskProfileRulesIndex(HttpClient client, String path, Resource scope) {
    super(client, path, scope);
  }

  public Class<RiskProfileRule> getViewType() {
    return RiskProfileRule.class;
  }

  public Maybe<RiskProfileRule> post(RiskProfileRuleForm form) {
    return super.post(form);
  }

  public Maybe<Page<RiskProfileRule>> get() {
    return (Maybe<Page<RiskProfileRule>>)(Maybe<?>) super._get();
  }

  public RiskProfileRuleResource id(String id) {
    return new RiskProfileRuleResource(getHttpClient(), getPath(), id);
  }

}
