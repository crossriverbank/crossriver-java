package io.finix.payments.resources;

import io.finix.payments.forms.BankAccountForm;
import io.finix.payments.forms.PaymentCardForm;
import io.finix.payments.forms.TokenAssociationForm;
import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Maybe;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.lib.Page;
import io.finix.payments.views.BankAccount;
import io.finix.payments.views.Instrument;
import io.finix.payments.views.PaymentCard;

public class InstrumentsIndex extends Index<Instrument, Page<Instrument>> {

  public InstrumentsIndex(HttpClient client, String path) {
    super(client, path);
  }

  public Class<Instrument> getViewType() {
    return Instrument.class;
  }

  public Maybe<PaymentCard> post(PaymentCardForm form) {
    return (Maybe<PaymentCard>)(Maybe<?>) super.post(form, PaymentCard.class);
  }

  public Maybe<BankAccount> post(BankAccountForm form) {
    return (Maybe<BankAccount>)(Maybe<?>) super.post(form, BankAccount.class);
  }

  public Maybe<PaymentCard> post(TokenAssociationForm form) {
    return (Maybe<PaymentCard>)(Maybe<?>) super.post(form, PaymentCard.class);
  }

  public Maybe<Page<Instrument>> get() {
    return (Maybe<Page<Instrument>>)(Maybe<?>) super._get();
  }

  public InstrumentResource id(String id) {
    return new InstrumentResource(getHttpClient(), getPath(), id);
  }

}
