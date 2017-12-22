package io.finix.payments.resources;

import io.finix.payments.forms.ApplicationForm;
import io.finix.payments.forms.CardTokenForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.lib.Page;
import io.finix.payments.views.InstrumentToken;


public class TokensIndex extends Index<InstrumentToken, Page<InstrumentToken>> {

  public TokensIndex(HttpClient client, String path, Resource scope) {
    super(client, path, scope);
  }

  public Class<InstrumentToken> getViewType() {
    return InstrumentToken.class;
  }

  public Maybe<InstrumentToken> post(ApplicationForm form) {
    return super.post(form);
  }

  public Maybe<Page<InstrumentToken>> get() {
    return (Maybe<Page<InstrumentToken>>) (Maybe<?>) super._get();
  }

  public Maybe<InstrumentToken> post(CardTokenForm form) {
    return super.post(form);
  }

}
