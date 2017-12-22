package io.finix.payments.forms;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenAssociationForm extends InstrumentForm {

  String identity;
  String token;

  @Builder.Default
  String type = "TOKEN";

}
