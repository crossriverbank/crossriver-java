package io.finix.payments.processing.client.component;

import io.finix.payments.processing.client.model.PaymentCardToken;
import io.finix.payments.processing.client.model.ReviewQueueItem;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.finix.payments.processing.client.exception.ProcessingClientException;
import io.finix.payments.processing.client.model.Application;
import io.finix.payments.processing.client.model.Authorization;
import io.finix.payments.processing.client.model.BankAccount;
import io.finix.payments.processing.client.model.Dispute;
import io.finix.payments.processing.client.model.Evidence;
import io.finix.payments.processing.client.model.Identity;
import io.finix.payments.processing.client.model.Merchant;
import io.finix.payments.processing.client.model.MerchantProfile;
import io.finix.payments.processing.client.model.PaymentCard;
import io.finix.payments.processing.client.model.PaymentInstrument;
import io.finix.payments.processing.client.model.Processor;
import io.finix.payments.processing.client.model.Refund;
import io.finix.payments.processing.client.model.RiskProfileRule;
import io.finix.payments.processing.client.model.Settlement;
import io.finix.payments.processing.client.model.SwipedPaymentCard;
import io.finix.payments.processing.client.model.Transfer;
import io.finix.payments.processing.client.model.User;
import io.finix.payments.processing.client.model.Verification;
import io.finix.payments.processing.client.model.Webhook;
import lombok.Getter;

@Getter
public class TypeRef<T> {
  public static final TypeRef<User> userTypeRef = new TypeRef<User>(new ParameterizedTypeReference<Resources<User>>() {
  }) {
  };
  public static final TypeRef<Application> applicationTypeRef = new TypeRef<Application>(new ParameterizedTypeReference<Resources<Application>>() {
  }) {
  };
  public static final TypeRef<Processor> processorsTypeRef = new TypeRef<Processor>(new ParameterizedTypeReference<Resources<Processor>>() {
  }) {
  };
  public static final TypeRef<Identity> identityTypeRef = new TypeRef<Identity>(new ParameterizedTypeReference<Resources<Identity>>() {
  }) {
  };
  public static final TypeRef<PaymentInstrument> paymentInstrumentTypeRef = new TypeRef<PaymentInstrument>(new ParameterizedTypeReference<Resources<PaymentInstrument>>() {
  }) {
  };
  public static final TypeRef<PaymentCardToken> paymentCardTokenTypeRef = new TypeRef<PaymentCardToken>(new ParameterizedTypeReference<Resources<PaymentCardToken>>() {
  }) {
  };
  public static final TypeRef<BankAccount> bankAccountTypeRef = new TypeRef<BankAccount>(new ParameterizedTypeReference<Resources<BankAccount>>() {
  }) {
  };
  public static final TypeRef<PaymentCard> paymentCardTypeRef = new TypeRef<PaymentCard>(new ParameterizedTypeReference<Resources<PaymentCard>>() {
  }) {
  };
  public static final TypeRef<PaymentCard> swipedPaymentCardTypeRef = new TypeRef<PaymentCard>(new ParameterizedTypeReference<Resources<SwipedPaymentCard>>() {
  }) {
  };
  public static final TypeRef<Merchant> merchantTypeRef = new TypeRef<Merchant>(new ParameterizedTypeReference<Resources<Merchant>>() {
  }) {
  };
  public static final TypeRef<Transfer> transferTypeRef = new TypeRef<Transfer>(new ParameterizedTypeReference<Resources<Transfer>>() {
  }) {
  };
  public static final TypeRef<Refund> refundTypeRef = new TypeRef<Refund>(new ParameterizedTypeReference<Resources<Refund>>() {
  }) {
  };
  public static final TypeRef<Authorization> authorizationTypeRef = new TypeRef<Authorization>(new ParameterizedTypeReference<Resources<Authorization>>() {
  }) {
  };
  public static final TypeRef<Settlement> settlementTypeRef = new TypeRef<Settlement>(new ParameterizedTypeReference<Resources<Settlement>>() {
  }) {
  };
  public static final TypeRef<Webhook> webhookTypeRef = new TypeRef<Webhook>(new ParameterizedTypeReference<Resources<Webhook>>() {
  }) {
  };
  public static final TypeRef<Dispute> disputeTypeRef = new TypeRef<Dispute>(new ParameterizedTypeReference<Resources<Dispute>>() {
  }) {
  };
  public static final TypeRef<Evidence> evidenceTypeRef = new TypeRef<Evidence>(new ParameterizedTypeReference<Resources<Evidence>>() {
  }) {
  };
  public static final TypeRef<Verification> verificationTypeRef = new TypeRef<Verification>(new ParameterizedTypeReference<Resources<Verification>>() {
  }) {
  };
  public static final TypeRef<Transfer> fundingTypeRef = new TypeRef<Transfer>(new ParameterizedTypeReference<Resources<Transfer>>() {
  }) {
  };
  public static final TypeRef<RiskProfileRule> riskProfileRuleTypeRef = new TypeRef<RiskProfileRule>(new ParameterizedTypeReference<Resources<RiskProfileRule>>() {
  }) {
  };
  public static final TypeRef<MerchantProfile> merchantProfilesTypeRef = new TypeRef<MerchantProfile>(new ParameterizedTypeReference<Resources<MerchantProfile>>() {
  }) {
  };

  public static final TypeRef<ReviewQueueItem> reviewQueueItemTypeRef = new TypeRef<ReviewQueueItem>(new ParameterizedTypeReference<Resources<ReviewQueueItem>>() {
  }) {
  };

  private Type type;
  private ParameterizedTypeReference ptr;

  public TypeRef() {
    this.type = getGenericType(getClass());
  }

  public TypeRef(ParameterizedTypeReference ptr) {
    this();
    this.ptr = ptr;
  }

  public static Type getGenericType(Class<?> clazz) {
    Type superclass = clazz.getGenericSuperclass();
    if (superclass instanceof Class) {
      throw new ProcessingClientException("No type parameter provided");
    }
    ParameterizedType parameterize = (ParameterizedType) superclass;
    return parameterize.getActualTypeArguments()[0];
  }
}
