package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class ProcessorUpdateForm implements Form {

  public String merchantProfile;
  public Map<String, String> config;

}
