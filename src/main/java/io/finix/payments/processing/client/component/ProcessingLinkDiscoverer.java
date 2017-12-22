package io.finix.payments.processing.client.component;

import org.springframework.hateoas.core.JsonPathLinkDiscoverer;
import org.springframework.http.MediaType;

public class ProcessingLinkDiscoverer extends JsonPathLinkDiscoverer {

  public ProcessingLinkDiscoverer() {
    super("$._links..['%s']..href", MediaType.ALL);
//        super("$._links..['%s']..href", MediaType.parseMediaType("application/vnd.finix.1.0.0+json"));
  }
}
