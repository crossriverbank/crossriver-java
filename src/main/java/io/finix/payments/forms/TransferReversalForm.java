package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

@Builder
public class TransferReversalForm implements Form {

  public Long amount;
  public Long fee;

}
