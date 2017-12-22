package io.finix.payments.interfaces;

import io.finix.payments.lib.HttpClient;
import io.finix.payments.lib.Operation;
import io.finix.payments.lib.Page;
import lombok.Getter;
import lombok.Setter;

public abstract class Index<V extends View, P extends Page<V>> {

  public abstract Class<V> getViewType();

  @Setter
  String path;

  @Getter @Setter
  HttpClient httpClient;

  @Getter @Setter
  Resource scope;

  protected Query query;

  public Index(HttpClient client, String path) {
    this.setHttpClient(client);
    this.setPath(path);
  }

  public Index(HttpClient client, String path, Resource scope) {
    this.setHttpClient(client);
    this.setPath(path);
    this.setScope(scope);
  }

  public String getPath() {
    if (getScope() == null)
      return this.path;
    return HttpClient.concat(scope.getPath(), this.path);
  }

  public <F extends Form> Maybe<V> post(F form) {
    return (Maybe<V>) (Operation.post(
      Operation.builder()
        .path(getPath())
        .output(getViewType())
        .client(getHttpClient())
        .build())).apply(form);
  }

  public <F extends Form> Maybe<V> post(F form, Class viewType) {
    return (Maybe<V>) (Operation.post(
      Operation.builder()
        .path(getPath())
        .output(viewType)
        .client(getHttpClient())
        .build())).apply(form);
  }

  public Maybe<Page<?>> _get() {
    return (Operation.query(
      Operation.builder()
        .path(getPath())
        .output(getViewType())
        .paginated(true)
        .client(getHttpClient())
        .build())).apply(null);
  }

  public Maybe<Page<?>> _get(Query query) {
    return (Operation.query(
      Operation.builder()
        .path(getPath() + query.toString())
        .output(getViewType())
        .paginated(true)
        .client(getHttpClient())
        .build())).apply(null);
  }

}
