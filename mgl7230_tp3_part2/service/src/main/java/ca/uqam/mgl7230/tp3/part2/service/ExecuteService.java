package ca.uqam.mgl7230.tp3.part2.service;

import ca.uqam.mgl7230.tp3.part2.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp3.part2.model.passenger.Passenger;
import com.example.model.FlightBackendPostRequest;

import java.io.FileWriter;
import java.io.IOException;

import static ca.uqam.mgl7230.tp3.part2.config.ServerInitializer.*;

public class ExecuteService implements Runnable {

    private final FlightBackendPostRequest flightPostRequest;
    private final FileWriter fileWriter;

    public ExecuteService(final FlightBackendPostRequest flightPostRequest,
                          final FileWriter fileWriter) {
        this.flightPostRequest = flightPostRequest;
        this.fileWriter = fileWriter;
    }

    @Override
    public void run() {
        String flightNumber = flightPostRequest.getFlightNumber();
        FlightInformation flightInformation = flightCatalog().getFlightInformation(flightNumber);
        Passenger passenger = passengerService().createPassenger(flightInformation, flightPostRequest);
        try {
            savePassengerInFlight().save(fileWriter, passenger, flightNumber);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            throw new RuntimeException("Error in executing task");
        }
    }
}
