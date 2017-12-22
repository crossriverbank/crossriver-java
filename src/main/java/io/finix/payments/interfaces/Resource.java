package io.finix.payments.interfaces;

import io.finix.payments.lib.HttpClient;
import io.finix.payments.lib.Operation;
import lombok.Getter;
import lombok.Setter;

public abstract class Resource<V extends View> {

  public abstract Class<V> getViewType();

  @Getter @Setter
  String id;

  @Getter @Setter
  String path;

  @Getter @Setter
  HttpClient httpClient;

  public Resource(HttpClient client, String path, String id) {
    this.setHttpClient(client);
    this.setPath(HttpClient.concat(path, id));
    this.setId(id);
  }

  public <S extends V> Maybe<S> get() {
    return (Maybe<S>) (Operation.get(
      Operation.builder()
        .path(getPath())
        .output(getViewType())
        .client(getHttpClient())
        .build())).apply(null);
  }

  public <F extends Form> Maybe<V> put(F form) {
    return (Maybe<V>) (Operation.put(
      Operation.builder()
        .path(getPath())
        .output(getViewType())
        .client(getHttpClient())
        .build())).apply(form);
  }

}
