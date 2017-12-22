package io.finix.payments.processing.client.component;

import lombok.Getter;

@Getter
public class ResourcesType {

  public static final ResourcesType USERS = new ResourcesType("users", TypeRef.userTypeRef);
  public static final ResourcesType APPLICATIONS = new ResourcesType("applications", TypeRef.applicationTypeRef);
  public static final ResourcesType PROCESSORS = new ResourcesType("processors", TypeRef.processorsTypeRef);
  public static final ResourcesType IDENTITIES = new ResourcesType("identities", TypeRef.identityTypeRef);
  public static final ResourcesType OWNER_IDENTITY = new ResourcesType("owner_identity", TypeRef.identityTypeRef);
  public static final ResourcesType TOKENS = new ResourcesType("tokens", TypeRef.paymentCardTokenTypeRef);
  public static final ResourcesType PAYMENT_INSTRUMENTS = new ResourcesType("payment_instruments", TypeRef.paymentInstrumentTypeRef);
  public static final ResourcesType BANK_ACCOUNTS = new ResourcesType("payment_instruments", TypeRef.bankAccountTypeRef);
  public static final ResourcesType PAYMENT_CARDS = new ResourcesType("payment_instruments", TypeRef.paymentCardTypeRef);
  public static final ResourcesType SWIPED_PAYMENT_CARDS = new ResourcesType("payment_instruments", TypeRef.swipedPaymentCardTypeRef);
  public static final ResourcesType MERCHANTS = new ResourcesType("merchants", TypeRef.merchantTypeRef);
  public static final ResourcesType UNDERWRITING = new ResourcesType("merchants", TypeRef.merchantTypeRef);
  public static final ResourcesType TRANSFERS = new ResourcesType("transfers", TypeRef.transferTypeRef);
  public static final ResourcesType AUTHORIZATIONS = new ResourcesType("authorizations", TypeRef.authorizationTypeRef);
  public static final ResourcesType REVERSALS = new ResourcesType("reversals", TypeRef.refundTypeRef);
  public static final ResourcesType SETTLEMENTS = new ResourcesType("settlements", TypeRef.settlementTypeRef);
  public static final ResourcesType WEBHOOKS = new ResourcesType("webhooks", TypeRef.webhookTypeRef);
  public static final ResourcesType DISPUTES = new ResourcesType("disputes", TypeRef.disputeTypeRef);
  public static final ResourcesType EVIDENCE = new ResourcesType("evidence", TypeRef.evidenceTypeRef);
  public static final ResourcesType VERIFICATIONS = new ResourcesType("verifications", TypeRef.verificationTypeRef);
  public static final ResourcesType FUNDING_TRANSFERS = new ResourcesType("funding_transfers", TypeRef.fundingTypeRef);
  public static final ResourcesType RISK_PROFILE_RULES = new ResourcesType("risk_profile_rules", TypeRef.riskProfileRuleTypeRef);
  public static final ResourcesType MERCHANT_PROFILES = new ResourcesType("merchant_profiles", TypeRef.merchantProfilesTypeRef);
  public static final ResourcesType REVIEW_QUEUE = new ResourcesType("review_queue", TypeRef.reviewQueueItemTypeRef);

  private String rel;
  private TypeRef typeRef;

  public ResourcesType(String rel, TypeRef typeRef) {
    this.rel = rel;
    this.typeRef = typeRef;
  }
}
