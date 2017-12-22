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
public class Transfer extends AbstractModel {

  public enum State {PENDING, SUCCEEDED, FAILED}

  private String source;
  private String destination;
  private String merchantIdentity;
  private Long amount;
  private String currency;
  private String processor;
  private Long fee;
  private State state;
  private String type;
  private String readyToSettleAt;
  public String idempotencyId;

  @Builder
  public Transfer(
    String source,
    String destination,
    String merchantIdentity,
    Long amount,
    String currency,
    String processor,
    Long fee,
    Map<String, String> tags
  ) {
    this.source = source;
    this.destination = destination;
    this.merchantIdentity = merchantIdentity;
    this.amount = amount;
    this.currency = currency;
    this.processor = processor;
    this.fee = fee;
    this.setTags(tags);
  }

  public ProcessingClient disputeClient() {
    ProcessingClient client = getClient();
    Resource<Transfer> transferResource = client.withResourceType(ResourcesType.TRANSFERS).fetchResource(getId());
    return client.withResourceType(ResourcesType.DISPUTES, transferResource);
  }

  public ProcessingClient reversalsClient() {
    ProcessingClient client = getClient();
    Resource<Transfer> transferResource = client.withResourceType(ResourcesType.TRANSFERS).fetchResource(getId());
    return client.withResourceType(ResourcesType.REVERSALS, transferResource);
  }

  public Refund reverse(long amount) {
    return reversalsClient().save(Refund.builder().refundAmount(amount).build());
  }
}
