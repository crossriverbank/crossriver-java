package io.finix.payments.lib;

import org.junit.Assert;
import org.junit.Test;

public class HttpClientTest {

  @Test
  public void testConcat() {
    String url = "http://example.com/items";
    Assert.assertEquals(url, HttpClient.concat("http://example.com", "items"));
    Assert.assertEquals(url, HttpClient.concat("http://example.com", "/items"));
    Assert.assertEquals(url, HttpClient.concat("http://example.com/", "items"));
    Assert.assertEquals(url, HttpClient.concat("http://example.com/", "/items"));
  }
}
