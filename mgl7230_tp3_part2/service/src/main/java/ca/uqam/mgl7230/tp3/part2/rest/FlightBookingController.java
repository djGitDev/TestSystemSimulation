package ca.uqam.mgl7230.tp3.part2.rest;

import ca.uqam.mgl7230.tp3.part2.service.ExecuteService;
import com.example.model.FlightBackendPostRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ca.uqam.mgl7230.tp3.part2.config.ServerInitializer.fileWriterProvider;

public class FlightBookingController implements HttpHandler {

    private final ObjectMapper mapper    = new ObjectMapper();

    @Override
    public void handle(final HttpExchange exchange) throws IOException {
        String flightId = UUID.randomUUID().toString();
        FileWriter fileWriter = fileWriterProvider().createFile("passengerData" + flightId + ".csv");

        StringBuilder requestBody = getRequestBody(exchange);
        JsonNode arrayNode = mapper.readTree(requestBody.toString());

        List<FlightBackendPostRequest> flightPostRequests = new ArrayList<>();
        for (JsonNode node : arrayNode) {
            FlightBackendPostRequest request = mapper.treeToValue(node, FlightBackendPostRequest.class);
            flightPostRequests.add(request);
        }

        flightPostRequests.forEach(flightPostRequest -> {
            ExecuteService task = new ExecuteService(flightPostRequest, fileWriter);
            task.run();
        });

        exchange.sendResponseHeaders(201, flightId.getBytes(StandardCharsets.UTF_8).length);
        fileWriter.close();

        // Set response headers and body
        OutputStream os = exchange.getResponseBody();
        os.write(flightId.getBytes(StandardCharsets.UTF_8));
        os.close();

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
