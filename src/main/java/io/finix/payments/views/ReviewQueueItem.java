package io.finix.payments.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.finix.payments.enums.RiskEntityType;
import io.finix.payments.enums.RiskReviewOutcome;
import io.finix.payments.enums.RiskReviewType;
import io.finix.payments.interfaces.View;
import lombok.Getter;

import java.util.Map;

@Getter
public class ReviewQueueItem implements View {

  public String completedAt;
  public RiskReviewType reviewType;
  public RiskEntityType entityType;
  public RiskReviewOutcome outcome;
  public String application;
  public String reviewedBy;
  public String entityId;
  public String id;
  public String createdAt;
  public String updatedAt;

  @JsonProperty("_links")
  public Map<String, Object> links;

}
