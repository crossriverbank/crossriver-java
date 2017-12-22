package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class ProcessorForm implements Form {

  public String type;
  public Map config;
  public String merchantProfile;

}
