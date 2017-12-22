package io.finix.payments.enums;

public enum CardAddressVerification {

  POSTAL_CODE_AND_STREET_MATCH,
  STREET_MATCH,
  POSTAL_CODE_MATCH,
  NO_ADDRESS,
  NO_MATCH,
  NOT_SUPPORTED,
  UNKNOWN

}
