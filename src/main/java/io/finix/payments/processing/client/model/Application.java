package io.finix.payments.processing.client.model;

import org.springframework.hateoas.Resource;

import java.util.Map;

import io.finix.payments.processing.client.ProcessingClient;
import io.finix.payments.processing.client.component.ResourcesType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Application extends AbstractModel {
  private String owner;
  private Entity entity;
  private Boolean enabled;
  private Boolean processingEnabled;
  private Boolean settlementEnabled;

  @Builder
  public Application(String owner, Entity entity, Boolean enabled, Map<String, String> tags, boolean processingEnabled, boolean settlementEnabled) {
    this.owner = owner;
    this.entity = entity;
    this.enabled = enabled;
    this.setTags(tags);
    this.processingEnabled = processingEnabled;
    this.settlementEnabled = settlementEnabled;
  }

  public ProcessingClient usersClient() {
    ProcessingClient client = getClient();
    Resource<User> applicationResource = client.withResourceType(ResourcesType.APPLICATIONS).fetchResource(getId());
    return client.withResourceType(ResourcesType.USERS, applicationResource);
  }

  public ProcessingClient ownerIdentitiesClient() {
    ProcessingClient client = getClient();
    Resource<Application> applicationResource = client.withResourceType(ResourcesType.APPLICATIONS).fetchResource(getId());
    return client.withResourceType(ResourcesType.OWNER_IDENTITY, applicationResource);
  }

  public ProcessingClient processorsClient() {
    ProcessingClient client = getClient();
    Resource<Application> applicationResource = client.withResourceType(ResourcesType.APPLICATIONS).fetchResource(getId());
    return client.withResourceType(ResourcesType.PROCESSORS, applicationResource);
  }

  public ProcessingClient tokensClient() {
    ProcessingClient client = getClient();
    Resource<Application> applicationResource = client.withResourceType(ResourcesType.APPLICATIONS).fetchResource(getId());
    return client.withResourceType(ResourcesType.TOKENS, applicationResource);
  }

  public ProcessingClient partnerUsersClient() {
    ProcessingClient client = getClient();
    Resource<Application> applicationResource = client.withResourceType(ResourcesType.APPLICATIONS).fetchResource(getId());
    return client.withResourceType(ResourcesType.USERS, applicationResource);
  }

  public Processor createProcessor(Processor processor) {
    return processorsClient().save(processor);
  }

  public User createPartnerUser(User user) {
    return partnerUsersClient().save(user);
  }

  public PaymentCardToken tokenizeCard(PaymentCard card) {
    return this.tokensClient().savePaymentCardToken(card);
  }
}
