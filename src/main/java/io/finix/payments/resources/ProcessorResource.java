package io.finix.payments.resources;

import io.finix.payments.forms.ProcessorUpdateForm;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.views.Processor;

public class ProcessorResource extends Resource<Processor> {

  public ProcessorResource(HttpClient client, String path, String id) {
    super(client, path, id);
  }

  public Class<Processor> getViewType() {
    return Processor.class;
  }

  public Maybe<Processor> get() {
    return super.get();
  }

  public Maybe<Processor> put(ProcessorUpdateForm form) {
    return super.put(form);
  }

}
