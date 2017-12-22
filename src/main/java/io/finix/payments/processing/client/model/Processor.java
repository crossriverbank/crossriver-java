package io.finix.payments.processing.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class Processor extends AbstractModel {
  private String name;
  private String type;
  private Map<String, String> config;
  private String defaultMerchantProfileId;

  public Processor() {
  }

  @Builder
  public Processor(String name, String type, Map<String, String> config, String defaultMerchantProfileId) {
    this.name = name;
    this.type = type;
    this.config = config;
    this.defaultMerchantProfileId = defaultMerchantProfileId;
  }
}
