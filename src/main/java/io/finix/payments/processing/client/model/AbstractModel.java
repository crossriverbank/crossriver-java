package io.finix.payments.processing.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.finix.payments.processing.client.ProcessingClient;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public abstract class AbstractModel {
  private String id;
  private Date createdAt;
  private Date updatedAt;
  protected Map<String, String> tags;

  @JsonIgnore
  protected ProcessingClient client;

  public ProcessingClient getClient() {
    if (client == null) {
      throw new UnsupportedOperationException("`client` is null. It must be setup by `withClient` function");
    }
    return client;
  }

  public <T extends AbstractModel> T withClient(ProcessingClient client) {
    this.client = client;
    return (T) this;
  }
}
