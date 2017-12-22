package io.finix.payments.processing.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Date {
  private int day;
  private int month;
  private int year;

  public Date() {
  }

  @Builder
  public Date(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }
}
