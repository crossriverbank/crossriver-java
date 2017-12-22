package io.finix.payments.processing.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Name {
  private String firstName;
  private String lastName;
  private String fullName;

  @JsonCreator
  public static Name parse(String value) {
    if (value == null || value.trim().isEmpty()) {
      return null;
    }
    String[] name = value.split("\\s+", 2);
    String firstName = name[0];
    String lastName = name.length > 1 ? name[1] : null;
    return new Name(firstName, lastName, value);
  }

}
