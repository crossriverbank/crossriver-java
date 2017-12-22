package io.finix.payments.forms;

import io.finix.payments.enums.LimitActionType;
import io.finix.payments.enums.RiskProfileRuleEntityType;
import io.finix.payments.enums.RiskProfileRuleType;
import io.finix.payments.enums.TrailingUnitType;
import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class RiskProfileRuleForm implements Form {

  public String riskProfile;

  public Boolean enabled;

  public Integer trailing;

  public TrailingUnitType trailingUnit;

  public RiskProfileRuleType type;

  public RiskProfileRuleEntityType entityType;

  public LimitActionType action;

  public Integer amount;

  public Integer count;

  public Integer ratio;

  public Map<String, String> tags;
}
