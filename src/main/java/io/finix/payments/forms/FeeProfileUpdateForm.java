package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;
import lombok.Builder;

import java.util.Map;

@Builder
public class FeeProfileUpdateForm implements Form {

  public Integer fixedFee;
  public Integer basisPoints;
  public Integer achFixedFee;
  public Integer achBasisPoints;
  public Boolean chargeInterchange;
  public String application;
  public Map<String, String> tags;

}
