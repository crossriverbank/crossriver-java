package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

@Builder
public class Date implements Form {

  public Integer day;
  public Integer month;
  public Integer year;

}
