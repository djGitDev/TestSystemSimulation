package ca.uqam.mgl7230.tp3.part1.service;

import ca.uqam.mgl7230.tp3.part1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp3.part1.model.passenger.Passenger;
import ca.uqam.mgl7230.tp3.part1.model.passenger.PassengerClass;
import com.example.model.FlightFrontendPostRequest;

public class BookingService {

    private final FlightPassengerService flightPassengerService;
    private final PassengerService passengerService;

    public BookingService(final FlightPassengerService flightPassengerService,
                          final PassengerService passengerService) {
        this.flightPassengerService = flightPassengerService;
        this.passengerService = passengerService;
    }

    public boolean isBooked(Passenger passenger,
                            final FlightInformation flightInformation,
                            FlightFrontendPostRequest flightFrontendPostRequest) {
        if (flightPassengerService.numberOfTotalSeatsAvailable() != 0) {
            if (PassengerClass.FIRST_CLASS.name().equalsIgnoreCase(passenger.getType().name())) {
                if (flightPassengerService.numberOfFirstClassSeatsAvailable() == 0) {
                    System.out.println("First Class is Full. Trying Business...");
                    flightFrontendPostRequest.setPassengerType("business");
                    passenger = passengerService.createPassenger(flightInformation, flightFrontendPostRequest);
                }
            }
            if (PassengerClass.BUSINESS_CLASS.name().equalsIgnoreCase(passenger.getType().name())) {
                if (flightPassengerService.numberOfBusinessClassSeatsAvailable() == 0) {
                    System.out.println("Business Class is Full. Trying Economy...");
                    flightFrontendPostRequest.setPassengerType("economy");
                    passenger = passengerService.createPassenger(flightInformation, flightFrontendPostRequest);
                }
            }
            if (PassengerClass.ECONOMY_CLASS.name().equalsIgnoreCase(passenger.getType().name())) {
                if (flightPassengerService.numberOfEconomyClassSeatsAvailable() == 0) {
                    System.out.println("Economy is Full. Try another type...");
                    return false;
                }
            }
            flightPassengerService.addPassenger(passenger);
            System.out.println("Passenger added successfully");
        } else {
            System.out.println("Flight is Full...");
            return false;
        }
        return true;
    }
}
