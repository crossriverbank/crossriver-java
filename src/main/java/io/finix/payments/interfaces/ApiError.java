package io.finix.payments.interfaces;

import io.finix.payments.views.CollectionView;
import io.finix.payments.views.errors.Errors;

import java.util.Objects;
import java.util.stream.Collectors;

public interface ApiError extends View {

  Integer getCode();
  String getMessage();
  CollectionView<Errors> getBody();

  default String getDetails() {
    return getBody().embedded.errors.stream()
      .map(e -> Objects.toString(e.getCode(), "") + " " +
        Objects.toString(e.getField(), "") + " " +
        Objects.toString(e.getMessage(), ""))
      .collect(Collectors.joining("; "));
  }

}
