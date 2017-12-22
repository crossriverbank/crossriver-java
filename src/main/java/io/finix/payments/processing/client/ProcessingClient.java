package io.finix.payments.processing.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import com.fasterxml.jackson.databind.type.SimpleType;
import io.finix.payments.processing.client.model.PaymentCard;
import io.finix.payments.processing.client.model.PaymentCardToken;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import io.finix.payments.processing.client.component.ProcessingLinkDiscoverer;
import io.finix.payments.processing.client.component.ResourcesType;
import io.finix.payments.processing.client.component.RestTemplateResponseErrorHandler;
import io.finix.payments.processing.client.exception.ProcessingClientException;
import io.finix.payments.processing.client.model.AbstractModel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProcessingClient {
  public static String BASE_URL_PROPERTY = "processing.url";

  protected ObjectMapper jacksonMapper;
  protected RestTemplate restTemplate;
  protected ProcessingLinkDiscoverer processingLinkDiscoverer;
  protected String basicAuthEncoded;
  protected URI baseUri;
  protected Traverson traverson;
  protected ResourcesType resourcesType;
  protected final String rootUrl;

  public ProcessingClient() {
    this(System.getProperty(BASE_URL_PROPERTY));
  }

  public ProcessingClient(String rootUrl) {
    this.rootUrl = rootUrl;
    init();
  }

  private void init() {
    this.updateBaseUri();

    this.jacksonMapper = new ObjectMapper()
      .registerModule(new Jackson2HalModule())
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .setSerializationInclusion(JsonInclude.Include.NON_NULL)
      .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
      .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    this.processingLinkDiscoverer = new ProcessingLinkDiscoverer();

    this.restTemplate = new RestTemplate();
    this.restTemplate.setMessageConverters(prepareMessageConverters(jacksonMapper));

    RestTemplateResponseErrorHandler errorHandler = new RestTemplateResponseErrorHandler();
    errorHandler.setJacksonMapper(jacksonMapper);
    this.restTemplate.setErrorHandler(errorHandler);

    this.updateTraverson();
  }

  /**
   * Take default converters, add MappingJackson2HttpMessageConverter and set current ObjectMapper
   * @param mapper
   * @return
   */
  private List<HttpMessageConverter<?>> prepareMessageConverters(ObjectMapper mapper) {
    List<HttpMessageConverter<?>> converters = new ArrayList<>(Traverson.getDefaultMessageConverters(MediaTypes.HAL_JSON));
    MappingJackson2HttpMessageConverter httpMessageConverter = converters.stream()
      .filter(c -> c instanceof MappingJackson2HttpMessageConverter)
      .findFirst()
      .map(c -> (MappingJackson2HttpMessageConverter) c)
      .orElse(null);
    if (httpMessageConverter == null) {
      httpMessageConverter = new MappingJackson2HttpMessageConverter();
      converters.add(httpMessageConverter);
    }
    httpMessageConverter.setObjectMapper(mapper);
    converters.add(new FormHttpMessageConverter());
    return converters;
  }

  public ProcessingClient withResourceType(ResourcesType resourcesType) {
    Assert.notNull(resourcesType);
    this.resourcesType = resourcesType;
    this.updateBaseUri();
    return this;
  }

  public ProcessingClient setupUserIdAndPassword(String userId, String password) {
    if (StringUtils.hasText(userId) && StringUtils.hasText(password)) {
      this.basicAuthEncoded = "Basic " + Base64.getEncoder().encodeToString(String.format("%s:%s", userId,
        password).getBytes());
    }
    else {
      this.basicAuthEncoded = null;
    }
    return this;
  }

  public ProcessingClient withResourceType(ResourcesType resourcesType, Resource relResource) {
    try {
      Assert.notNull(resourcesType);
      Assert.notNull(relResource);

      this.resourcesType = resourcesType;

      String url = relResource.hasLink(this.resourcesType.getRel()) ?
        relResource.getLink(this.resourcesType.getRel()).getHref() :
        relResource.getLink("self").getHref() + "/" + this.resourcesType.getRel();
      this.baseUri = new URI(url);
      this.updateTraverson();
    }
    catch (Exception e) {
      throw new ProcessingClientException(e);
    }
    return this;
  }

  public ProcessingClient usersClient() {
    return this.withResourceType(ResourcesType.USERS);
  }

  public ProcessingClient applicationsClient() {
    return this.withResourceType(ResourcesType.APPLICATIONS);
  }

  public ProcessingClient identitiesClient() {
    return this.withResourceType(ResourcesType.IDENTITIES);
  }

  public ProcessingClient paymentCardsClient() {
    return this.withResourceType(ResourcesType.PAYMENT_CARDS);
  }

  public ProcessingClient swipedPaymentCardsClient() {
    return this.withResourceType(ResourcesType.SWIPED_PAYMENT_CARDS);
  }

  public ProcessingClient bankAccountsClient() {
    return this.withResourceType(ResourcesType.BANK_ACCOUNTS);
  }

  public ProcessingClient transfersClient() {
    return this.withResourceType(ResourcesType.TRANSFERS);
  }

  public ProcessingClient reviewQueueClient() {
    return this.withResourceType(ResourcesType.REVIEW_QUEUE);
  }

  public ProcessingClient authorizationsClient() {
    return this.withResourceType(ResourcesType.AUTHORIZATIONS);
  }

  public ProcessingClient webhookClient() {
    return this.withResourceType(ResourcesType.WEBHOOKS);
  }

  public ProcessingClient merchantsClient() {
    return this.withResourceType(ResourcesType.MERCHANTS);
  }

  public ProcessingClient settlementsClient() {
    return this.withResourceType(ResourcesType.SETTLEMENTS);
  }

  public ProcessingClient verificationsClient() {
    return this.withResourceType(ResourcesType.VERIFICATIONS);
  }

  public ProcessingClient riskProfileRuleClient() {
    return this.withResourceType(ResourcesType.RISK_PROFILE_RULES);
  }

  public ProcessingClient merchantProfileClient() {
    return this.withResourceType(ResourcesType.MERCHANT_PROFILES);
  }

  public <T extends AbstractModel> T fetch(String id) {
    return (T) fetchResource(id).getContent();
  }

  public <T extends AbstractModel> Resource<T> fetchResource(String id) {
    Assert.hasText(id);
    String url = baseUri.toString() + "/" + id;
    return get(url);
  }

  public <T extends AbstractModel> T fetch() {
    return (T) fetchResource().getContent();
  }

  public <T extends AbstractModel> Resource<T> fetchResource() {
    String url = baseUri.toString();
    return get(url);
  }

  public <T extends AbstractModel> T save(T resource) {
    return saveResource(resource).getContent();
  }

  public PaymentCardToken savePaymentCardToken(PaymentCard card) {
    return this.saveAs(card, PaymentCardToken.class);
  }

  public PaymentCard associateToken(PaymentCardToken token) {
    return this.saveAs(token, PaymentCard.class);
  }

  public <T extends AbstractModel, U extends AbstractModel> U saveAs(T resource, Class<U> responseType) {
    return saveResourceAs(resource, responseType).getContent();
  }

  public <T extends AbstractModel> Resource<T> saveResource(T resource) {
    Assert.notNull(resource);
    String url = baseUri.toString();
    return post(url, resource);
  }

  public <T extends AbstractModel, U extends AbstractModel> Resource<U> saveResourceAs(T resource, Class<U> responseType) {
    Assert.notNull(resource);
    String url = baseUri.toString();
    return postAs(url, resource, responseType);
  }

  public <T extends AbstractModel> T save(String id, T partial) {
    return saveResource(id, partial).getContent();
  }

  public <T extends AbstractModel> Resource<T> saveResource(String id, T partial) {
    Assert.hasText(id);
    Assert.notNull(partial);
    String url = baseUri.toString() + "/" + id;
    return put(url, partial);
  }

  public <T extends Resources<? extends AbstractModel>> ResourcesIterator<T> resourcesIterator() {
    Assert.notNull(this.resourcesType);
    return new ResourcesIterator<>(this);
  }

  public <T extends Resources<? extends AbstractModel>> Iterable<T> resourcesIterable() {
    return () -> resourcesIterator();
  }

  protected Traverson updateTraverson() {
    return updateTraverson(this.baseUri);
  }

  protected Traverson updateTraverson(URI uri) {
    this.traverson = new Traverson(uri, MediaType.ALL);
    this.traverson.setLinkDiscoverers(Arrays.asList(processingLinkDiscoverer));
    this.traverson.setRestOperations(restTemplate);
    return this.traverson;
  }



  protected void updateBaseUri() {
    try {
//      String url = StringUtils.hasText(rootUrl) ? rootUrl : System.getProperty(BASE_URL_PROPERTY);
      String rel = this.resourcesType == null ? "" : this.resourcesType.getRel();
      this.baseUri = new URI(String.format("%s/%s", rootUrl, rel));
      if (log.isDebugEnabled()) {
        log.debug("base-uri={}", baseUri.toString());
      }

      //update traverson because baseUri changed
      this.updateTraverson();
    }
    catch (Exception e) {
      throw new ProcessingClientException(e);
    }
  }

  public <T extends AbstractModel> Resource<T> get(String url) {
    return exchange(HttpMethod.GET, url, null);
  }

  public <T extends AbstractModel> Resource<T> put(String url, T partial) {
    return exchange(HttpMethod.PUT, url, partial);
  }

  public <T extends AbstractModel> Resource<T> post(String url, T resource) {
    return exchange(HttpMethod.POST, url, resource);
  }

  public <T extends AbstractModel, U extends AbstractModel> Resource<U> postAs(String url, T resource, Class<U> responseType) {
    return exchangeAs(HttpMethod.POST, url, resource, responseType);
  }

  public <T extends AbstractModel> Resource<T> upload(File attachment) throws IOException {
    return post(baseUri.toString(), attachment);
  }

  public <T extends AbstractModel> Resource<T> post(String url, File attachment) throws IOException {
    return exchangeFile(HttpMethod.POST, url, attachment);
  }

  protected <T extends AbstractModel> Resource<T> exchangeFile(HttpMethod method, String url, File attachment) throws IOException {
    MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
    data.add("file", new FileSystemResource(attachment));
    HttpHeaders headers = defaultHttpHeaders();
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(data, headers);
    ResponseEntity<String> response = restTemplate.exchange(url, method, requestEntity, String.class);
    String resBody = response.getBody();
    return parseResource(resBody);
  }

  protected <T extends AbstractModel> Resource<T> exchange(HttpMethod method, String url, T resource) {
    HttpHeaders headers = defaultHttpHeaders();
    HttpEntity<String> requestEntity = resource == null ? new HttpEntity<>(headers) : new HttpEntity<>(toJsonString(resource), headers);
    ResponseEntity<String> responseEntity = restTemplate.exchange(url, method, requestEntity, String.class);
    String resBody = responseEntity.getBody();
    return parseResource(resBody);
  }

  protected <T extends AbstractModel, U extends AbstractModel> Resource<U> exchangeAs(HttpMethod method, String url, T resource, Class<U> responseType) {
    HttpHeaders headers = defaultHttpHeaders();
    HttpEntity<String> requestEntity = resource == null ? new HttpEntity<>(headers) : new HttpEntity<>(toJsonString(resource), headers);
    ResponseEntity<String> responseEntity = restTemplate.exchange(url, method, requestEntity, String.class);
    String resBody = responseEntity.getBody();
    return parseResourceU(resBody, responseType);
  }

  protected String toJsonString(Object resource) {
    try {
      String json = jacksonMapper.writeValueAsString(resource);
      if (log.isDebugEnabled()) {
        log.debug("converted resource resource to json: {}", json);
      }
      return json;
    }
    catch (JsonProcessingException e) {
      throw new ProcessingClientException(e);
    }
  }

  protected <T extends AbstractModel> Resource<T> parseResource(String jsonString) {
    try {
      T content = parse(jsonString);
      JavaType resType = jacksonMapper.constructType(new ParameterizedTypeReference<Resource<T>>() {
      }.getType());
      Resource res = jacksonMapper.readValue(jsonString, resType);
      return new Resource<>(content, res.getLinks());
    }
    catch (Exception e) {
      throw new ProcessingClientException(e);
    }
  }

  protected <U extends AbstractModel> Resource<U> parseResourceU(String jsonString, Class<U> responseType) {
    try {
      U content = parseU(jsonString, responseType);
      JavaType resType = jacksonMapper.constructType(new ParameterizedTypeReference<Resource<U>>() {
      }.getType());
      Resource res = jacksonMapper.readValue(jsonString, resType);
      Resource<U> parsed = new Resource<>(content, res.getLinks());
      return parsed;
    }
    catch (Exception e) {
      throw new ProcessingClientException(e);
    }
  }

  protected <T extends AbstractModel> T parse(String jsonString) {
    try {
      if (log.isDebugEnabled()) {
        log.debug("parse json string: {}", jsonString);
      }
      JavaType resType = jacksonMapper.constructType(resourcesType.getTypeRef().getType());
      T t = jacksonMapper.readValue(jsonString, resType);
      return (T) t.withClient(this);
    }
    catch (Exception e) {
      throw new ProcessingClientException(e);
    }
  }

  protected <U extends AbstractModel> U parseU(String jsonString, Class<U> responseType) {
    try {
      if (log.isDebugEnabled()) {
        log.debug("parse json string: {}", jsonString);
      }
      JavaType resType = jacksonMapper.constructType(resourcesType.getTypeRef().getType());
      U t = jacksonMapper.readValue(jsonString, resType);
      return t;
    }
    catch (Exception e) {
      throw new ProcessingClientException(e);
    }
  }

  protected HttpHeaders defaultHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    if (StringUtils.hasText(this.basicAuthEncoded)) {
      headers.set(HttpHeaders.AUTHORIZATION, this.basicAuthEncoded);
    }
    return headers;
  }

  public class ResourcesIterator<T extends Resources<? extends AbstractModel>> implements Iterator<T> {

    @Getter
    private T current;

    private boolean hasNext = true;

    private ProcessingClient client;

    public ResourcesIterator(ProcessingClient client) {
      this.current = null;
      this.client = client;
    }

    public boolean hasNext() {
      return hasNext;
    }

    public T getPage() {
      this.current = (T) (
        traverson.follow()
          .withHeaders(defaultHttpHeaders())
          .toObject(resourcesType.getTypeRef().getPtr())
      );
      this.current.getContent().forEach(res -> res.withClient(this.client));
      Link nextLink = this.current.getLink("next");
      this.hasNext = (nextLink != null);
      return this.current;
    }

    public T next() {
      if (this.current == null) return getPage();
      Link nextLink = this.current.getLink("next");
      try {
        traverson = updateTraverson(new URI(nextLink.getHref()));
      } catch (Exception e) {
      }
      return getPage();
    }
  }

}
