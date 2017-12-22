package io.finix.payments.resources;

import io.finix.payments.forms.TransferForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.lib.Page;
import io.finix.payments.lib.UrlQuery;
import io.finix.payments.views.Transfer;

public class TransfersIndex extends Index<Transfer, Page<Transfer>> {

  public TransfersIndex(HttpClient client, String path) {
    super(client, path);
  }

  public TransfersIndex(HttpClient client, String path, Resource scope) {
    super(client, path, scope);
  }

  public Class<Transfer> getViewType() {
    return Transfer.class;
  }

  public Maybe<Transfer> post(TransferForm form) {
    return (Maybe<Transfer>)(Maybe<?>) super.post(form, Transfer.class);
  }

  public TransferResource id(String id) {
    return new TransferResource(getHttpClient(), getPath(), id);
  }

  public Maybe<Page<Transfer>> get() {
    return (Maybe<Page<Transfer>>)(Maybe<?>) super._get(this.query);
  }

  public TransfersQuery query() {
    this.query = new TransfersQuery(this);
    return (TransfersQuery) this.query;
  }

  public class TransfersQuery extends UrlQuery<TransfersIndex> {

    public Field<TransfersQuery> idempotencyId() {
      return this.field("idempotency_id");
    }

    public TransfersQuery(TransfersIndex index) {
      super(index);
    }

    public Maybe<Page<Transfer>> get() {
      return this.index.get();
    }
  }

}
