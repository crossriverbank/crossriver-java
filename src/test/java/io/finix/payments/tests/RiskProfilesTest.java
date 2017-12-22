package io.finix.payments.tests;

import com.google.common.collect.ImmutableMap;
import io.finix.payments.ApiClient;
import io.finix.payments.ProcessingTest;
import io.finix.payments.enums.LimitActionType;
import io.finix.payments.enums.RiskProfileRuleEntityType;
import io.finix.payments.enums.RiskProfileRuleType;
import io.finix.payments.enums.TrailingUnitType;
import io.finix.payments.forms.RiskProfileForm;
import io.finix.payments.forms.RiskProfileRuleForm;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.lib.Page;
import io.finix.payments.views.*;
import org.junit.Test;

public class RiskProfilesTest extends ProcessingTest {

  @Test
  public void testCreate() {
    MerchantsTest.Platform platform = MerchantsTest.provisionPlatform("VISA_V1");
    ApiClient api = platform.apiClient;
    ApiClient platformApi = platform.platformApiClient;
    Application application = platform.application;
    RiskProfileForm form = RiskProfileForm.builder()
      .application(application.id)
      .build();
    Maybe<RiskProfile> response = platformApi.riskProfiles.post(form);
    if (! response.succeeded()) {
      echo(response.error());
      throw new RuntimeException("Failed to create risk profile");
    }
    RiskProfile riskProfile = response.view();
    echo(riskProfile);
  }

  @Test
  public void testMerchantRiskProfileConfiguration() {
    MerchantsTest.Platform platform = MerchantsTest.provisionPlatform("VISA_V1");
    ApiClient api = platform.apiClient;
    ApiClient platformApi = platform.platformApiClient;
    Application application = platform.application;
    Identity ownerIdentity = api.identities.id(application.owner).get().view();

    Page<Merchant> merchantPage = api.identities.id(ownerIdentity.id).merchants.get().view();
    Merchant ownerMerchant = merchantPage.contents.get(0);

    MerchantProfile merchantProfile = api.merchantProfiles.id(ownerMerchant.merchantProfile).get().view();
    RiskProfile riskProfile = api.riskProfiles.id(merchantProfile.riskProfile).get().view();
    echo(riskProfile);

    RiskProfileRuleForm form = RiskProfileRuleForm.builder()
      .riskProfile(riskProfile.id)
      .enabled(true)
      .tags(ImmutableMap.of("fraud", "rules"))
      .trailing(1)
      .trailingUnit(TrailingUnitType.HOURS)
      .type(RiskProfileRuleType.TRAILING)
      .entityType(RiskProfileRuleEntityType.TRANSFER)
      .action(LimitActionType.FREEZE)
      .amount(0)
      .count(1)
      .ratio(0)
      .build();

    Maybe<RiskProfileRule> request = platformApi.riskProfileRules.post(form);

    if (! request.succeeded()) {
      echo(request.error());
      throw new RuntimeException("Failed to create risk profile rule");
    }

    RiskProfileRule rule = request.view();
    echo(rule);
  }
}
