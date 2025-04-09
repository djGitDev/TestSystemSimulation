package ca.uqam.mgl7230.tp3.part1.rest;

import com.example.model.FlightFrontendPostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static ca.uqam.mgl7230.tp3.part1.config.ApplicationInitializer.flightCatalog;
import static ca.uqam.mgl7230.tp3.part1.service.ExecuteService.execute;

public class FlightBookingController implements HttpHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(final HttpExchange exchange) throws IOException {
        StringBuilder requestBody = getRequestBody(exchange);
        String response = "Received: " + requestBody;
        System.out.println(response);
        List<FlightFrontendPostRequest> flightFrontendPostRequests = getFlightFrontendPostRequests(requestBody);
        if (flightFrontendPostRequests.isEmpty()) {
            exchange.sendResponseHeaders(400, "Bad Request".getBytes(StandardCharsets.UTF_8).length);
            OutputStream os = exchange.getResponseBody();
            os.write("Bad Request".getBytes(StandardCharsets.UTF_8));
            os.close();
            return;
        }
        exchange.sendResponseHeaders(201, response.getBytes(StandardCharsets.UTF_8).length);
        exchange.getResponseHeaders().set("Content-Type", "text/plain");
        String result = execute(flightFrontendPostRequests);

        // Set response headers and body
        OutputStream os = exchange.getResponseBody();
        os.write(result.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    private List<FlightFrontendPostRequest> getFlightFrontendPostRequests(final StringBuilder requestBody) {
        String body = requestBody.toString();
        try {
            if (doesNotContainWritePassengerType(body)) {
                throw new JsonMappingException("wrong type of passenger class");
            }
            List<FlightFrontendPostRequest> flightFrontendPostRequests = objectMapper.readValue(body, new TypeReference<>() {
            });
            AtomicBoolean foundFlight = new AtomicBoolean(true);
            flightFrontendPostRequests.forEach(request -> {
                if (flightCatalog().getFlightInformation(request.getFlightNumber()) == null) {
                    foundFlight.set(false);
                }
             });
            if (!foundFlight.get()) {
                throw new JsonMappingException("wrong flight, try again");
            }
            return flightFrontendPostRequests;
        } catch (JsonProcessingException e) {
            return List.of();
        }
    }

    private static boolean doesNotContainWritePassengerType(final String body) {
        return !(body.contains("first") || body.contains("business") || body.contains("economy"));
    }

    @NotNull
    private static StringBuilder getRequestBody(final HttpExchange exchange) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }
        return requestBody;
    }
}
