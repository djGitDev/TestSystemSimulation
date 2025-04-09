package ca.uqam.mgl7230.tp3.part2.integration.support;

import com.example.model.FlightBackendPostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

public class HttpExchangeSupport {

    public static HttpExchange buildHttpExchange(FlightBackendPostRequest flightPostRequest) {
        return new HttpExchange() {
            @Override
            public Headers getRequestHeaders() {
                return null;
            }

            @Override
            public Headers getResponseHeaders() {
                return new Headers();
            }

            @Override
            public URI getRequestURI() {
                return null;
            }

            @Override
            public String getRequestMethod() {
                return "";
            }

            @Override
            public HttpContext getHttpContext() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getRequestBody() {
                ObjectMapper objectMapper = new ObjectMapper();
                byte[] jsonBytes;
                try {
                    ArrayNode arrayNode = objectMapper.createArrayNode();
                    arrayNode.add(objectMapper.valueToTree(flightPostRequest));
                    jsonBytes = objectMapper.writeValueAsBytes(arrayNode);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                return new ByteArrayInputStream(jsonBytes);
            }

            @Override
            public OutputStream getResponseBody() {
                return new OutputStream() {
                    @Override
                    public void write(int b) {

                    }
                };
            }

            @Override
            public void sendResponseHeaders(int rCode, long responseLength) {

            }

            @Override
            public InetSocketAddress getRemoteAddress() {
                return null;
            }

            @Override
            public int getResponseCode() {
                return 0;
            }

            @Override
            public InetSocketAddress getLocalAddress() {
                return null;
            }

            @Override
            public String getProtocol() {
                return "";
            }

            @Override
            public Object getAttribute(String name) {
                return null;
            }

            @Override
            public void setAttribute(String name, Object value) {

            }

            @Override
            public void setStreams(InputStream i, OutputStream o) {

            }

            @Override
            public HttpPrincipal getPrincipal() {
                return null;
            }
        };
    }
}
