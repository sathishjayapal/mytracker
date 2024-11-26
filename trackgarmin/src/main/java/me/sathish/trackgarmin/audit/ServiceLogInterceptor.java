package me.sathish.trackgarmin.audit;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

@Component
public class ServiceLogInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        logRequestDetails(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponseDetails(response);
        return new ResponseWrapper(response);
    }

    private void logRequestDetails(HttpRequest request, byte[] body) throws IOException {
        System.out.println("Request URI: " + request.getURI());
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request Headers: " + request.getHeaders());
        System.out.println("Request Body: " + new String(body));
    }

    private void logResponseDetails(ClientHttpResponse response) throws IOException {
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Headers: " + response.getHeaders());
        System.out.println("Response Body: " + response.getBody());
    }

    public static class ResponseWrapper implements ClientHttpResponse {
        private final ClientHttpResponse response;
        private byte[] body;

        public ResponseWrapper(ClientHttpResponse response) {
            this.response = response;
        }

        @Override
        public HttpStatusCode getStatusCode() throws IOException {
            return response.getStatusCode();
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return response.getRawStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return response.getStatusText();
        }

        @Override
        public void close() {
            response.close();
        }

        @Override
        public InputStream getBody() throws IOException {
            if (body == null) {
                InputStream responseStream = response.getBody();
                body = StreamUtils.copyToByteArray(responseStream);
            }
            return new ByteArrayInputStream(body);
        }

        @Override
        public HttpHeaders getHeaders() {
            return response.getHeaders();
        }
    }
}
