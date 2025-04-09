package ca.uqam.mgl7230.tp3.part1.service;

import ca.uqam.mgl7230.tp3.part1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp3.part1.model.passenger.*;
import ca.uqam.mgl7230.tp3.part1.utils.DistanceCalculator;
import com.example.model.FlightFrontendPostRequest;

public class PassengerService {

    private final DistanceCalculator distanceCalculator;
    private final PassengerClassService passengerClassService;

    public PassengerService(final DistanceCalculator distanceCalculator,
                            final PassengerClassService passengerClassService) {
        this.distanceCalculator = distanceCalculator;
        this.passengerClassService = passengerClassService;
    }

    public Passenger createPassenger(final FlightInformation flightInformation,
                                     final FlightFrontendPostRequest flightPostRequest) {
        String passengerPassport = flightPostRequest.getPassengerPassport();
        String passengerName = flightPostRequest.getPassengerName();
        int passengerAge = flightPostRequest.getPassengerAge();
        PassengerClass passengerClass = passengerClassService.getPassengerClass(flightPostRequest.getPassengerType());
        switch (passengerClass) {
            case PassengerClass.FIRST_CLASS -> {
                return new FirstClassPassenger(passengerPassport, passengerName, passengerAge,
                        distanceCalculator.calculate(flightInformation));
            }
            case PassengerClass.BUSINESS_CLASS -> {
                return new BusinessClassPassenger(passengerPassport, passengerName, passengerAge,
                        distanceCalculator.calculate(flightInformation));
            }
            default -> {
                return new EconomyClassPassenger(passengerPassport, passengerName, passengerAge,
                        distanceCalculator.calculate(flightInformation));
            }
        }
    }
}
