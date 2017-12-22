package io.finix.payments;

import io.finix.payments.interfaces.ApiError;
import io.finix.payments.interfaces.Form;
import io.finix.payments.interfaces.View;
import io.finix.payments.lib.JsonMapper;
import io.finix.payments.lib.Operation;
import org.junit.Before;

import java.io.IOException;

public class ProcessingTest {

  @Before
  public void setup() {
    Operation.DEBUG = true;
    JsonMapper.PRODUCTION = false;
  }

  public static String url = "https://api-staging.finix.io";

  public static ApiClient unauthenticatedApi = ApiClient.builder().url(url).build();

  public static <V extends View> void echo(V view) {
    System.out.println("\tResponse:");
    System.out.println();
    try {
      System.out.println(JsonMapper.indent(JsonMapper.prettyPrint(view)));
    } catch (IOException e) {
      throw new RuntimeException("IO Exception serializing API response to json");
    }
  }

  public static <F extends Form> void echo(F form) {
    System.out.println("\tRequest:");
    System.out.println();
    try {
      System.out.println(JsonMapper.indent(JsonMapper.prettyPrint(form)));
    } catch (IOException e) {
      throw new RuntimeException("IO Exception serializing Form to json");
    }
  }

  public static void echo(ApiError error) {
    System.out.print("\t\t");
    System.out.println(error.getCode());
    System.out.print("\t\t");
    System.out.println(error.getMessage());
    System.out.print("\t\t");
    System.out.println(error.getDetails());
  }

}
