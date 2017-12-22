package io.finix.payments.views.errors;

import io.finix.payments.interfaces.ApiError;
import io.finix.payments.views.CollectionView;
import lombok.Builder;
import lombok.Getter;

@Builder
public class ProcessingApiError implements ApiError {

  @Getter
  Integer code;

  @Getter
  CollectionView<Errors> body;

  @Getter
  String message;

}
