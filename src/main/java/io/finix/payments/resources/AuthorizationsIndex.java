package io.finix.payments.resources;

import io.finix.payments.forms.AuthorizationCreateForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.lib.Page;
import io.finix.payments.lib.UrlQuery;
import io.finix.payments.views.Authorization;

public class AuthorizationsIndex extends Index<Authorization, Page<Authorization>> {

  public AuthorizationsIndex(HttpClient client, String path) {
    super(client, path);
  }

  public Class<Authorization> getViewType() {
    return Authorization.class;
  }

  public Maybe<Authorization> post(AuthorizationCreateForm form) {
    return super.post(form);
  }

  public AuthorizationResource id(String id) {
    return new AuthorizationResource(getHttpClient(), getPath(), id);
  }

  public Maybe<Page<Authorization>> get() {
    return (Maybe<Page<Authorization>>)(Maybe<?>) super._get(this.query);
  }

  public AuthorizationsQuery query() {
    this.query = new AuthorizationsQuery(this);
    return (AuthorizationsQuery) this.query;
  }

  public class AuthorizationsQuery extends UrlQuery<AuthorizationsIndex> {

    public Field<AuthorizationsQuery> idempotencyId() {
      return this.field("idempotency_id");
    }

    public AuthorizationsQuery(AuthorizationsIndex index) {
      super(index);
    }

    public Maybe<Page<Authorization>> get() {
      return this.index.get();
    }
  }

}
