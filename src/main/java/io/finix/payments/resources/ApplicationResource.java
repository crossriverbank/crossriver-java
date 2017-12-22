package io.finix.payments.resources;

import io.finix.payments.interfaces.Maybe;
import io.finix.payments.interfaces.Resource;
import io.finix.payments.lib.HttpClient;
import io.finix.payments.views.Application;

public class ApplicationResource extends Resource<Application> {

  public ProcessorsIndex processors;

  public TokensIndex tokens;

  public IdentitiesIndex identities;

  public RiskProfilesIndex riskProfiles;

  public ApplicationResource(HttpClient client, String path, String id) {
    super(client, path, id);
    this.processors = new ProcessorsIndex(client, "/processors", this);
    this.tokens = new TokensIndex(client, "/tokens", this);
    this.identities = new IdentitiesIndex(client, "/identities", this);
    this.riskProfiles = new RiskProfilesIndex(client, "/risk_profiles", this);
  }

  public Class<Application> getViewType() {
    return Application.class;
  }

  public Maybe<Application> get() {
    return super.get();
  }

}
