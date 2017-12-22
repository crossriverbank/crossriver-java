package io.finix.payments.processing.client.model;

import io.finix.payments.processing.client.ProcessingClient;
import io.finix.payments.processing.client.component.ResourcesType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.Resource;

import java.util.Map;

@Getter
@Setter
@ToString
public class Identity extends AbstractModel {
  private Entity entity;

  public Identity() {
  }

  @Builder
  public Identity(Entity entity, Map<String, String> tags) {
    this.entity = entity;
    this.setTags(tags);
  }

  public ProcessingClient settlementTransfersClient() {
    ProcessingClient client = getClient();
    Resource<Identity> identityResource = client.withResourceType(ResourcesType.IDENTITIES).fetchResource(getId());
    return client.withResourceType(ResourcesType.SETTLEMENTS, identityResource);
  }

  public ProcessingClient underwritingMerchantsClient() {
    ProcessingClient client = getClient();
    Resource<Identity> identityResource = client.withResourceType(ResourcesType.IDENTITIES).fetchResource(getId());
    return client.withResourceType(ResourcesType.UNDERWRITING, identityResource);
  }

  public ProcessingClient verificationsClient() {
    ProcessingClient client = getClient();
    Resource<Identity> identityResource = client.withResourceType(ResourcesType.IDENTITIES).fetchResource(getId());
    return client.withResourceType(ResourcesType.VERIFICATIONS, identityResource);
  }

  public ProcessingClient merchantUsersClient() {
    ProcessingClient client = getClient();
    Resource<Identity> identityResource = client.withResourceType(ResourcesType.IDENTITIES).fetchResource(getId());
    return client.withResourceType(ResourcesType.USERS, identityResource);
  }

  public Merchant provisionMerchantOn(Merchant merchant) {
    return underwritingMerchantsClient().save(merchant);
  }

  public Settlement createSettlement(Settlement settlement) {
    return settlementTransfersClient().save(settlement);
  }

  public Verification verify(Verification verification) {
    return verificationsClient().save(verification);
  }

  public User createMerchantUser(User user) {
    return merchantUsersClient().save(user);
  }
}
