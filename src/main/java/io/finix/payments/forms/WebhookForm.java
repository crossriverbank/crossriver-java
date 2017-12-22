package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebhookForm implements Form {

  String url;
  Boolean enabled;

}
