package io.finix.payments.processing.client.model;

import io.finix.payments.processing.client.ProcessingClient;
import io.finix.payments.processing.client.component.ResourcesType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.Resource;

@Getter
@Setter
@ToString
public class Merchant extends AbstractModel {
  private String processor;
  private String merchantProfile;
  private String identity;
  private String verification;
  private boolean processingEnabled;
  public String onboardingState;

  public Merchant() {
  }

  @Builder
  public Merchant(String processor, String merchantProfile) {
    this.processor = processor;
    this.merchantProfile = merchantProfile;
  }

  public ProcessingClient verificationsClient() {
    ProcessingClient client = getClient();
    Resource<Merchant> merchantResource = client.withResourceType(ResourcesType.MERCHANTS).fetchResource(getId());
    return client.withResourceType(ResourcesType.VERIFICATIONS, merchantResource);
  }

  public Verification verify(Verification verification) {
    return verificationsClient().save(verification);
  }

}
