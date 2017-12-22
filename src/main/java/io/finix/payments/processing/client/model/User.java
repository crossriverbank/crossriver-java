package io.finix.payments.processing.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class User extends AbstractModel {

  public enum ROLE {
    ROLE_ADMIN, ROLE_PLATFORM, ROLE_PARTNER, ROLE_MERCHANT
  }

  private String password;
  private Boolean enabled;
  private ROLE role;

  public User() {
  }

  @Builder
  public User(Boolean enabled, Map<String, String> tags, ROLE role) {
    this.enabled = enabled;
    this.setTags(tags);
    this.role = role;
  }
}
