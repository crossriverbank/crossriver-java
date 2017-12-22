package io.finix.payments.processing.client.model;

import org.springframework.hateoas.Resource;

import io.finix.payments.processing.client.ProcessingClient;
import io.finix.payments.processing.client.component.ResourcesType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Settlement extends AbstractModel {

  private String identity;
  private String processor;
  private String currency;
  private Integer totalAmount;
  private Integer totalFees;
  private Integer totalFee;
  private Integer netAmount;
  private String transfer;
  private String destination;
  private String type;

  public Settlement() {
  }

  public ProcessingClient transferClient() {
    ProcessingClient client = getClient();
    Resource<Settlement> settlementResource = client.withResourceType(ResourcesType.SETTLEMENTS).fetchResource(getId());
    return client.withResourceType(ResourcesType.TRANSFERS, settlementResource);
  }

  public ProcessingClient fundingTransfersClient() {
    ProcessingClient client = getClient();
    Resource<Transfer> settlementResource = client.withResourceType(ResourcesType.SETTLEMENTS).fetchResource(getId());
    return client.withResourceType(ResourcesType.FUNDING_TRANSFERS, settlementResource);
  }
}
