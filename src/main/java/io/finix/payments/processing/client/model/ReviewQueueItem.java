package io.finix.payments.processing.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewQueueItem extends AbstractModel {

  String reviewType;

  String entityType;

  String outcome;

  String application;

  String reviewedBy;

  String entityId;
}
