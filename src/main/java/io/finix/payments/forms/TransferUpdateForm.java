package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class TransferUpdateForm implements Form {

  public Map<String, String> tags;

}
