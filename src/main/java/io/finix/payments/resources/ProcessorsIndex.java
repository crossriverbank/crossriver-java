package io.finix.payments.resources;

import io.finix.payments.forms.ProcessorForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.lib.Page;
import io.finix.payments.views.Processor;

public class ProcessorsIndex extends Index<Processor, Page<Processor>> {

  public ProcessorsIndex(HttpClient client, String path) {
    super(client, path);
  }

  public ProcessorsIndex(HttpClient client, String path, Resource scope) {
    super(client, path, scope);
  }

  public Class<Processor> getViewType() {
    return Processor.class;
  }

  public Maybe<Processor> post(ProcessorForm form) {
    return (Maybe<Processor>)(Maybe<?>) super.post(form, Processor.class);
  }

  public Maybe<Page<Processor>> get() {
    return (Maybe<Page<Processor>>)(Maybe<?>) super._get();
  }

  public ProcessorResource id(String id) {
    return new ProcessorResource(getHttpClient(), getPath(), id);
  }

}
