package ca.uqam.mgl7230.tp3.part1.service.mapper;

import ca.uqam.mgl7230.tp3.part1.model.passenger.Passenger;
import ca.uqam.mgl7230.tp3.part1.service.PassengerClassService;
import com.example.model.FlightFrontendPostRequest;

public class FlightBackendRequestMapper {

    private final PassengerClassService passengerClassService;

    public FlightBackendRequestMapper(final PassengerClassService passengerClassService) {
        this.passengerClassService = passengerClassService;
    }

    public FlightFrontendPostRequest map(final String flightNumber, final Passenger passenger) {
        return new FlightFrontendPostRequest()
                .flightNumber(flightNumber)
                .passengerPassport(passenger.getPassport())
                .passengerName(passenger.getName())
                .passengerAge(passenger.getAge())
                .passengerType(passengerClassService.getPassengerType(passenger.getType()));
    }
}
