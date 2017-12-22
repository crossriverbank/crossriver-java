package io.finix.payments.processing.client.model;

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
public class MerchantProfile extends AbstractModel {
  private String feeProfile;
  private String platformFeeProfile;
  private String riskProfile;
  private String reserveProfile;

}
