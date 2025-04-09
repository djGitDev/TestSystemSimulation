package ca.uqam.mgl7230.tp3.part1.adapter.rest;

import com.example.model.FlightFrontendPostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SavePassengers {

    ResponseEntity<String> call(List<FlightFrontendPostRequest> FlightFrontendPostRequests) throws JsonProcessingException;
}
