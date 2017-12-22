package io.finix.payments.lib;

import io.finix.payments.interfaces.Form;
import io.finix.payments.interfaces.View;

import java.io.IOException;
import java.util.function.Function;

import lombok.Builder;
import okhttp3.Response;

@Builder
public class Operation {

  public static boolean DEBUG = false;

  public final String path;
  public final Class input;
  public final Class output;
  public final HttpClient client;
  public boolean paginated;


  public static <U extends Form, V extends View> Function<U, io.finix.payments.interfaces.Maybe<V>> post(
    Operation op) {
    return (U form) -> {
      try {
        String json = JsonMapper.serialize(form);
        if (DEBUG) {
          System.out.print("POST " + op.path);
          System.out.println("\t\t" + "UserResource-Agent: " + op.client.user + " " + op.client.password);
          System.out.println("\tRequest:");
          System.out.println(JsonMapper.indent(JsonMapper.prettyPrint(form)));
        }
        Response response = op.client.post(op.path, json);
        if (DEBUG) {
          System.out.println("HTTP " + Integer.toString(response.code()));
        }
        if (response.code() == 201) {
          return Maybe.of(JsonMapper.parse(response.body().string(), op.output));
        } else {
          return Maybe.of(HttpClient.toError(response));
        }
      } catch (IOException e) {
        throw new RuntimeException(e.getMessage());
      }
    };
  }

  public static <U extends Form, V extends View> Function<U, io.finix.payments.interfaces.Maybe<V>> put(
    Operation op) {
    return (U form) -> {
      try {
        String json = JsonMapper.serialize(form);
        if (DEBUG) {
          System.out.println("PUT " + op.path);
          System.out.println("\t\t" + "UserResource-Agent: " + op.client.user + " " + op.client.password);
          System.out.println("\tRequest:");
          System.out.println(JsonMapper.indent(JsonMapper.prettyPrint(form)));
        }
        Response response = op.client.put(op.path, json);
        if (DEBUG) {
          System.out.println("HTTP " + Integer.toString(response.code()));
        }
        if (response.code() >= 200 && response.code() < 300) {
          return Maybe.of(JsonMapper.parse(response.body().string(), op.output));
        } else {
          return Maybe.of(HttpClient.toError(response));
        }
      } catch (IOException e) {
        throw new RuntimeException(e.getMessage());
      }
    };
  }

  public static <V extends View> Function<String, io.finix.payments.interfaces.Maybe<V>> get(
    Operation op) {
    return (String path) -> {
      try {
        if (DEBUG) {
          System.out.println("GET " + op.path);
          System.out.println("\t\t" + "UserResource-Agent: " + op.client.user + " " + op.client.password);
        }
        Response response = op.client.get(op.path);
        if (DEBUG) {
          System.out.println("HTTP " + Integer.toString(response.code()));
        }
        if (response.code() == 200) {
          return Maybe.of(JsonMapper.parse(response.body().string(), op.output));
        } else {
          return Maybe.of(HttpClient.toError(response));
        }
      } catch (IOException e) {
        throw new RuntimeException(e.getMessage());
      }
    };
  }

  public static Function<String, io.finix.payments.interfaces.Maybe<Page<?>>> query(
    Operation op) {
    return (String path) -> {
      try {
        if (DEBUG) {
          System.out.println("GET " + op.path);
          System.out.println("\t\t" + "UserResource-Agent: " + op.client.user + " " + op.client.password);
        }
        Response response = op.client.get(op.path);
        if (DEBUG) {
          System.out.println("HTTP " + Integer.toString(response.code()));
        }
        if (response.code() == 200) {
          return Maybe.of(JsonMapper.parsePage(response.body().string(), op.output, op.client));
        } else {
          return Maybe.of(HttpClient.toError(response));
        }
      } catch (IOException e) {
        throw new RuntimeException(e.getMessage());
      }
    };
  }

}
