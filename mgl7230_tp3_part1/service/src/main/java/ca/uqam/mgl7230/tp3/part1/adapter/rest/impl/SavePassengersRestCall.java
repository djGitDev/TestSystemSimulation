package ca.uqam.mgl7230.tp3.part1.adapter.rest.impl;

import ca.uqam.mgl7230.tp3.part1.adapter.rest.SavePassengers;
import com.example.model.FlightFrontendPostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class SavePassengersRestCall implements SavePassengers {

    private final RestTemplate restTemplate;

    public SavePassengersRestCall(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> call(final List<FlightFrontendPostRequest> requestBody) {
        String port = "8080";
        String url = "http://localhost:" + port + "/flight/backend";
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        requestBody.forEach(request
                -> arrayNode.add(mapper.valueToTree(request)));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ArrayNode> requestEntity = new HttpEntity<>(arrayNode, headers);
        try {
            ResponseEntity<String> response =  restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Response Status Code: " + response.getStatusCode());
                System.out.println("Response Body: " + response.getBody());
                return response;
            } else {
                System.out.println("Response Status Code: " + response.getStatusCode());
                System.out.println("Response Body: " + response.getBody());
                System.err.println("Request failed with status code: " + response.getStatusCode());
                return response;
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while calling backend: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}