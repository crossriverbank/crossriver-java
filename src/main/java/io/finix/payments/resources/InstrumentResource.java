package io.finix.payments.resources;

import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.views.Instrument;

public class InstrumentResource extends Resource<Instrument> {

  public VerificationsIndex verifications;

  public InstrumentResource(HttpClient client, String path, String id) {
    super(client, path, id);
    this.verifications = new VerificationsIndex(client, "/verifications", this);
  }

  public Class<Instrument> getViewType() {
    return Instrument.class;
  }

  public <S extends Instrument> Maybe<S> get() {
    return super.get();
  }

}
