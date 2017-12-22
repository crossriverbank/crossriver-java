package io.finix.payments.processing.client.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.finix.payments.processing.client.exception.EmbeddedErrorResponse;
import io.finix.payments.processing.client.exception.ProcessingClientException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Setter
@Slf4j
public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {

  private ObjectMapper jacksonMapper;

  public void handleError(ClientHttpResponse response) throws IOException {
    ProcessingClientException processingClientException;
    try {
      String errorBody = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
      if (log.isDebugEnabled()) {
        log.debug("Error body: {}", errorBody);
      }
      processingClientException = new ProcessingClientException("Error response when processing request",
        jacksonMapper.readValue(errorBody, EmbeddedErrorResponse.class));
    }
    catch (Exception e) {
      processingClientException = new ProcessingClientException(e);
    }
    throw processingClientException;
  }
}
