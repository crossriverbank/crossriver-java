package io.finix.payments.forms;

import io.finix.payments.interfaces.Form;

import java.util.Currency;
import java.util.Map;

public abstract class InstrumentForm implements Form {

  String type;

  String identity;
  String applicationId;
  Currency currency;
  Map<String, String> tags;

}
