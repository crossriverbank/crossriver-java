package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.interfaces.View;
import lombok.Getter;

@Getter
public class CollectionView<T> implements View {

  public Integer total;

  @JsonProperty("_embedded")
  public T embedded;

}
