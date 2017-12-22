package io.finix.payments.views;

import io.finix.payments.interfaces.View;
import lombok.Getter;

@Getter
public class Date implements View {

  public Integer day;
  public Integer month;
  public Integer year;

}
