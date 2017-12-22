package io.finix.payments.lib;

import io.finix.payments.interfaces.ApiError;

class Maybe<View> implements io.finix.payments.interfaces.Maybe {

  private View value;
  private ApiError error;

  public static <View> Maybe of(View view) {
    return new Maybe(view, null);
  }

  public static Maybe of(ApiError error) {
    return new Maybe(null, error);
  }

  private Maybe(View value, ApiError error) {
    this.value = value;
    this.error = error;
  }

  public Boolean succeeded() {
    return this.error == null;
  }

  public Boolean ok() {
    return this.error == null;
  }

  public View get() {
    if (this.value == null) {
      throw new RuntimeException("Cannot access empty value");
    }
    return this.value;
  }

  public View view() {
    return get();
  }

  public ApiError error() {
    return this.error;
  }
}
