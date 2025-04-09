package ca.uqam.mgl7230.tp3.part1.service.mapper;

import ca.uqam.mgl7230.tp3.part1.model.passenger.PassengerClass;
import ca.uqam.mgl7230.tp3.part1.model.passenger.PassengerKeyConstants;
import ca.uqam.mgl7230.tp3.part1.service.PassengerClassService;
import com.example.model.FlightFrontendPostRequest;

import java.util.Map;

import static ca.uqam.mgl7230.tp3.part1.model.passenger.PassengerKeyConstants.*;

public class FlightFrontendRequestMapper {

    private final PassengerClassService passengerClassService;

    public FlightFrontendRequestMapper(final PassengerClassService passengerClassService) {
        this.passengerClassService = passengerClassService;
    }

    public FlightFrontendPostRequest map(final String flightNumber,
                                         final Map<PassengerKeyConstants, Object> passengerMap) {
        return new FlightFrontendPostRequest()
                .flightNumber(flightNumber)
                .passengerPassport(String.valueOf(passengerMap.get(PASSENGER_PASSPORT)))
                .passengerName(String.valueOf(passengerMap.get(PASSENGER_NAME)))
                .passengerAge(Integer.parseInt(String.valueOf(passengerMap.get(PASSENGER_AGE))))
                .passengerType(passengerClassService
                        .getPassengerType((PassengerClass) passengerMap.get(PASSENGER_CLASS)));
    }
}
