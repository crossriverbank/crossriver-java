package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.enums.LimitActionType;
import io.finix.payments.enums.RiskProfileRuleEntityType;
import io.finix.payments.enums.RiskProfileRuleType;
import io.finix.payments.enums.TrailingUnitType;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.Map;

@Getter
public class RiskProfileRule implements View {

  public Boolean enabled;
  public Map<String,String> tags;
  public String riskProfile;
  public Integer trailing;
  public TrailingUnitType trailingUnit;
  public RiskProfileRuleType type;
  public RiskProfileRuleEntityType entityType;
  public LimitActionType action;
  public Integer amount;
  public Integer count;
  public Integer ratio;
  public String id;
  public String createdAt;
  public String updatedAt;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
