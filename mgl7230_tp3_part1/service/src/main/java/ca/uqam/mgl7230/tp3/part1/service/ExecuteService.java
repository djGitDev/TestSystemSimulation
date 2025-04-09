package ca.uqam.mgl7230.tp3.part1.service;

import ca.uqam.mgl7230.tp3.part1.model.passenger.Passenger;
import ca.uqam.mgl7230.tp3.part1.model.passenger.PassengerKeyConstants;
import com.example.model.FlightFrontendPostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ca.uqam.mgl7230.tp3.part1.config.ApplicationInitializer.*;

public class ExecuteService {

    public static String execute(final List<FlightFrontendPostRequest> requests) throws IOException {
        List<FlightFrontendPostRequest> flightFrontendPostRequests = new ArrayList<>();
        flightPassengerService().initializeFlightService(requests.getFirst().getFlightNumber());
        requests.forEach(request -> {
            String flightNumber = request.getFlightNumber();
            Passenger passenger = passengerService()
                    .createPassenger(flightCatalog().getFlightInformation(flightNumber), request);
            if (bookingService().isBooked(passenger, flightCatalog().getFlightInformation(flightNumber), request)) {
                flightFrontendPostRequests.add(request);
            }
        });
        ResponseEntity<String> call = savePassengers().call(flightFrontendPostRequests);
        return call.getBody();
    }

    public static void execute() throws JsonProcessingException {
        String flightNumber = flightPromptService().getFlightInformation(scanner()).getFlightNumber();
        flightPassengerService().initializeFlightService(flightNumber);
        boolean shouldContinue = true;
        List<FlightFrontendPostRequest> frontendPostRequests = new ArrayList<>();
        while (shouldContinue) {
            Map<PassengerKeyConstants, Object> passengerData =
                    passengerPromptService().getPassengerData(scanner());
            FlightFrontendPostRequest flightFrontendPostRequests = frontendMapper().map(flightNumber, passengerData);
            Passenger passenger = passengerService()
                    .createPassenger(flightCatalog().getFlightInformation(flightNumber), flightFrontendPostRequests);
            if (bookingService().isBooked(
                    passenger,
                    flightCatalog().getFlightInformation(flightNumber),
                    flightFrontendPostRequests)) {
                frontendPostRequests.add(backendMapper().map(flightNumber, passenger));
            }
            System.out.println("Seats available: " + flightPassengerService().numberOfTotalSeatsAvailable());
            System.out.println("Continue adding passengers to this flight? yes or no");
            String continueChoice = scanner().nextLine();
            if ("no".equalsIgnoreCase(continueChoice)) {
                if (savePassengers().call(frontendPostRequests).getStatusCode().is2xxSuccessful()) {
                    shouldContinue = false;
                }
                scanner().close();
            }
        }
    }
}
