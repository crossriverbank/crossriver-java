package io.finix.payments.lib;

import io.finix.payments.interfaces.Index;
import io.finix.payments.interfaces.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UrlQuery<T extends Index<?, ?>> implements Query {

  protected T index;

  private List<String> predicates = new ArrayList<>();

  public UrlQuery(T index) {
    this.index = index;
  }

  public Field field(String value) {
    return new Field(this, value);
  }

  public String toString() {
    if (predicates.size() == 0) {
      return "";
    }
    return "?" + predicates.stream()
      .collect(Collectors.joining("&"));
  }

  public class Field<Q extends UrlQuery> {

    private Q query;
    private String value;

    public Field(Q query, String value) {
      this.query = query;
      this.value = value;
    }

    public Q eq(String val) {
      predicates.add(this.value + "=" + val);
      return this.query;
    }
  }

}
