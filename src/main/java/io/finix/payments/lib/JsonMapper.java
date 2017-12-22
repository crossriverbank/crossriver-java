package io.finix.payments.lib;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import io.finix.payments.interfaces.View;
import io.finix.payments.views.CollectionView;
import io.finix.payments.views.errors.Errors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class JsonMapper {

  private static final ObjectMapper mapper = new ObjectMapper();
  private static Boolean configured = false;

  public static Boolean PRODUCTION = true;

  public static void configureMapper() {
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    mapper.setPropertyNamingStrategy(
      PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    configured = true;
    if (PRODUCTION) {
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
  }

  public static String serialize(Object form) throws IOException {
    if (!configured)
      configureMapper();
    String output = mapper.writeValueAsString(form);
    return output;
  }

  public static String prettyPrint(Object form) throws IOException {
    if (!configured)
      configureMapper();
    String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(form);
    return output;
  }

  public static String indent(String json) {
    return "\t\t" + json.replace("\n", "\n\t\t");
  }

  public static <T> T parse(String json, Class<T> type) throws IOException {
    if (!configured)
      configureMapper();
    JsonNode jsonNode = mapper.readTree(json);
    T result = mapper.convertValue(jsonNode, type);
    return result;
  }

  public static <T extends View> Page<T> parsePage(String json, Class<T> type, HttpClient client) throws IOException {
    if (!configured)
      configureMapper();
    JsonNode jsonNode = mapper.readTree(json);
    Page<T> result = mapper.convertValue(jsonNode, new TypeReference<Page<T>>() {});
    result.setHttpClient(client);
    result.setType(type);
    result.contents = new ArrayList<>();
    if (result.embedded != null) {
      List<LinkedHashMap> items = result.embedded.getItems();
      for (LinkedHashMap item : items) {
        result.contents.add(type.cast(JsonMapper.convert(item, type)));
      }
    }
    return result;
  }

  public static CollectionView<Errors> parseError(String json) throws IOException {
    if (!configured)
      configureMapper();
    JsonNode jsonNode = mapper.readTree(json);
    return mapper.convertValue(jsonNode, new TypeReference<CollectionView<Errors>>() {
    });
  }

  public static <T> T convert(LinkedHashMap map, Class<T> type) throws IOException {
    if (!configured)
      configureMapper();
    T result = mapper.convertValue(map, type);
    return result;
  }
}
