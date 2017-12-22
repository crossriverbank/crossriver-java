package io.finix.payments.interfaces;

public interface Maybe<View> {

  Boolean succeeded();

  Boolean ok();

  View get();

  View view();

  ApiError error();

}
