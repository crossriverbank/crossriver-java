package io.finix.payments.processing.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Webhook extends AbstractModel {
  private String url;
  private String application;

  public Webhook() {
  }

  @Builder
  public Webhook(String url) {
    this.url = url;
  }
}
