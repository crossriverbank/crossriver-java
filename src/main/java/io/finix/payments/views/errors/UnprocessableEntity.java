package io.finix.payments.views.errors;

import io.finix.payments.interfaces.ApiError;
import io.finix.payments.views.CollectionView;
import lombok.Builder;
import lombok.Getter;

@Builder
public class UnprocessableEntity implements ApiError {

  @Getter
  final Integer code = 422;

  @Getter
  CollectionView<Errors> body;

  @Getter
  String message;

}
