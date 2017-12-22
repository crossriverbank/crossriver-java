package io.finix.payments.processing.client.model;

import io.finix.payments.processing.client.ProcessingClient;
import io.finix.payments.processing.client.component.ResourcesType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.Resource;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
@ToString
public class Dispute extends AbstractModel {
  public enum State {PENDING, SUCCEEDED, FAILED}

  public enum Reason {
    TECHNICAL,
    CLERICAL,
    QUALITY,
    FRAUD;
  }

  private int amount;
  private State state;
  private String transfer;
  private Reason reason;

  public Dispute() {
  }

  public ProcessingClient evidenceClient() {
    ProcessingClient client = getClient();
    Resource<Dispute> disputeResource = client.withResourceType(ResourcesType.DISPUTES).fetchResource(getId());
    return client.withResourceType(ResourcesType.EVIDENCE, disputeResource);
  }

  public Evidence uploadEvidence(File evidence) throws IOException {
    return (Evidence) evidenceClient().upload(evidence).getContent();
  }
}
