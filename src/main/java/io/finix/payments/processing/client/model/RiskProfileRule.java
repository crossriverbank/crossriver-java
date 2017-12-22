package io.finix.payments.processing.client.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskProfileRule extends AbstractModel {
  private String riskProfile;
  private boolean enabled;
  private int trailing;
  private String trailingUnit;
  private String type;
  private String entityType;
  private String action;
  private int amount;
  private int count;
  private int ratio;
  Map<String, String> tags;
}
