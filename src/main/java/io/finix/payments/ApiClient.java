package io.finix.payments;

import io.finix.payments.lib.HttpClient;
import io.finix.payments.resources.*;

public class ApiClient {

  public UsersIndex users;
  public ApplicationsIndex applications;
  public IdentitiesIndex identities;
  public InstrumentsIndex instruments;
  public InstrumentsIndex paymentInstruments;
  public AuthorizationsIndex authorizations;
  public TransfersIndex transfers;
  public WebhooksIndex webhooks;
  public SettlementsIndex settlements;
  public MerchantsIndex merchants;
  public RiskProfilesIndex riskProfiles;
  public MerchantProfilesIndex merchantProfiles;
  public RiskProfileRulesIndex riskProfileRules;

  private String url;
  public HttpClient httpClient;

  public ApiClient(String url, HttpClient httpClient) {
    this.url = url;
    this.httpClient = httpClient;

    this.users = new UsersIndex(httpClient, "/users");
    this.applications = new ApplicationsIndex(httpClient, "/applications");
    this.identities = new IdentitiesIndex(httpClient, "/identities");
    this.paymentInstruments = new InstrumentsIndex(httpClient, "/payment_instruments");
    this.instruments = this.paymentInstruments;  // alias
    this.authorizations = new AuthorizationsIndex(httpClient, "/authorizations");
    this.transfers = new TransfersIndex(httpClient, "/transfers");
    this.webhooks = new WebhooksIndex(httpClient, "/webhooks");
    this.settlements = new SettlementsIndex(httpClient, "/settlements");
    this.merchants = new MerchantsIndex(httpClient, "/merchants");
    this.riskProfiles = new RiskProfilesIndex(httpClient, "/risk_profiles");
    this.merchantProfiles = new MerchantProfilesIndex(httpClient, "/merchant_profiles");
    this.riskProfileRules = new RiskProfileRulesIndex(httpClient, "/risk_profile_rules");
  }

  public static Builder builder() {
    return new ApiClient.Builder();
  }

  public static class Builder {

    private String url;
    private String user;
    private String password;

    public Builder url(String url) {
      this.url = url;
      return this;
    }

    public Builder user(String user) {
      this.user = user;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public ApiClient build() {
      HttpClient client = new HttpClient(url, user, password);
      return new ApiClient(url, client);
    }
  }
}
