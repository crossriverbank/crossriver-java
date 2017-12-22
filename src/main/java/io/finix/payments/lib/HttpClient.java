package io.finix.payments.lib;

import io.finix.payments.interfaces.ApiError;
import io.finix.payments.views.CollectionView;
import io.finix.payments.views.errors.*;
import okhttp3.*;

import java.io.IOException;

public class HttpClient {

  static final OkHttpClient httpClient = new OkHttpClient();
  private static final String contentType = "application/vnd.json+api";
  private static final MediaType JSON = MediaType.parse(contentType);

  public String baseUrl;
  public String user;
  public String password;
  private String credentials;

  public HttpClient(String url, String user, String key) {
    this.baseUrl = url;
    this.user = user;
    this.password = key;
    if (user != null && password != null) {
      credentials = Credentials.basic(user, password);
    }
  }

  public static ApiError toError(Response response) throws IOException {
    String str = response.body().string();
    CollectionView<Errors> body = JsonMapper.parseError(str);
    switch (response.code()) {
      case 400:
        return BadRequest.builder().body(body).message(response.message()).build();
      case 401:
        return Unauthorized.builder().body(body).message(response.message()).build();
      case 402:
        return UpstreamProcessorError.builder().body(body).message(response.message()).build();
      case 404:
        return NotFound.builder().body(body).message(response.message()).build();
      case 422:
        return UnprocessableEntity.builder().body(body).message(response.message()).build();
      case 500:
        return InternalServerError.builder().body(body).message(response.message()).build();
      default:
        return ProcessingApiError.builder().code(response.code()).body(body).message(response.message()).build();
    }
  }

  public Response get(String path) throws IOException {
    String url = concat(baseUrl, path);
    Request.Builder requestBuilder = new Request.Builder()
      .header("Content-Type", contentType)
      .url(url)
      .get();
    return request(requestBuilder);
  }

  public Response getUrl(String url) throws IOException {
    Request.Builder requestBuilder = new Request.Builder()
      .header("Content-Type", contentType)
      .url(url)
      .get();
    return request(requestBuilder);
  }

  public Response post(String path, String json) throws IOException {
    String url = concat(baseUrl, path);
    RequestBody body = RequestBody.create(JSON, json);
    Request.Builder requestBuilder = new Request.Builder()
      .url(url)
      .header("Content-Type", contentType)
      .post(body);
    return request(requestBuilder);
  }

  public Response put(String path, String json) throws IOException {
    String url = concat(baseUrl, path);
    RequestBody body = RequestBody.create(JSON, json);
    Request.Builder requestBuilder = new Request.Builder()
      .url(url)
      .put(body);
    return request(requestBuilder);
  }

  public Response request(Request.Builder requestBuilder) throws IOException {
    if (credentials != null) {
      requestBuilder.addHeader("Authorization", credentials);
    }
    Request request = requestBuilder.build();
    return httpClient.newCall(request).execute();
  }

  public static String concat(String base, String path) {
    StringBuilder builder = new StringBuilder();

    if (base.substring(base.length() - 1).equals("/"))
      builder.append(base.substring(0, base.length() - 1));
    else
      builder.append(base);

    builder.append("/");

    if (path.substring(0, 1).equals("/"))
      builder.append(path.substring(1, path.length()));
    else
      builder.append(path);

    return builder.toString();
  }

}
