package io.finix.payments.processing.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SwipedPaymentCard extends PaymentInstrument {
  private String keySerialNumber;
  private String formatId;
  private String encryptedTrackData;
  private Boolean readTrack1;
  private Boolean readTrack2;

  public SwipedPaymentCard() {
    this.setType(PaymentInstrumentType.SWIPED_PAYMENT_CARD);
  }

  @Builder
  public SwipedPaymentCard(String identity, Name name, String keySerialNumber,
                           String formatId, String encryptedTrackData, Boolean readTrack1,
                           Boolean readTrack2) {
    this();
    this.setName(name);
    this.setIdentity(identity);
    this.setKeySerialNumber(keySerialNumber);
    this.setEncryptedTrackData(encryptedTrackData);
    this.setFormatId(formatId);
    this.setReadTrack1(readTrack1);
    this.setReadTrack2(readTrack2);
  }
}
