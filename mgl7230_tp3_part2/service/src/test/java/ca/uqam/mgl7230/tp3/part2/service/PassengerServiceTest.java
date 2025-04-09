package ca.uqam.mgl7230.tp3.part2.service;

import ca.uqam.mgl7230.tp3.part2.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp3.part2.model.passenger.*;
import ca.uqam.mgl7230.tp3.part2.utils.DistanceCalculator;
import com.example.model.FlightBackendPostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PassengerServiceTest {

    private static final String PASSENGER_PASSPORT = "passengerPassport";
    private static final String PASSENGER_NAME = "passengerName";
    private static final Integer PASSENGER_AGE = 0;

    @InjectMocks
    private PassengerService passengerService;
    @Mock
    private DistanceCalculator distanceCalculator;
    @Mock
    private FlightInformation flightInformation;
    @Mock
    private FlightBackendPostRequest flightPostRequest;
    @Mock
    private PassengerClassService passengerClassService;

    @BeforeEach
    void setup() {
        given(flightPostRequest.getPassengerPassport()).willReturn(PASSENGER_PASSPORT);
        given(flightPostRequest.getPassengerName()).willReturn(PASSENGER_NAME);
        given(flightPostRequest.getPassengerAge()).willReturn(PASSENGER_AGE);
    }

    @Test
    void createFirstClassPassenger() {
        // Given
        String firstClass = "first";
        given(flightPostRequest.getPassengerType()).willReturn(firstClass);
        given(passengerClassService.getPassengerClass(firstClass)).willReturn(PassengerClass.FIRST_CLASS);
        given(distanceCalculator.calculate(flightInformation)).willReturn(0);

        // When
        Passenger passenger = passengerService.createPassenger(flightInformation, flightPostRequest);

        // Then
        assertThat(passenger).isInstanceOf(FirstClassPassenger.class);
    }

    @Test
    void createBusinessClassPassenger() {
        // Given
        String firstClass = "business";
        given(flightPostRequest.getPassengerType()).willReturn(firstClass);
        given(passengerClassService.getPassengerClass(firstClass)).willReturn(PassengerClass.BUSINESS_CLASS);
        given(distanceCalculator.calculate(flightInformation)).willReturn(0);

        // When
        Passenger passenger = passengerService.createPassenger(flightInformation, flightPostRequest);

        // Then
        assertThat(passenger).isInstanceOf(BusinessClassPassenger.class);
    }

    @Test
    void createEconomyClassPassenger() {
        // Given
        String firstClass = "economy";
        given(flightPostRequest.getPassengerType()).willReturn(firstClass);
        given(passengerClassService.getPassengerClass(firstClass)).willReturn(PassengerClass.ECONOMY_CLASS);
        given(distanceCalculator.calculate(flightInformation)).willReturn(0);

        // When
        Passenger passenger = passengerService.createPassenger(flightInformation, flightPostRequest);

        // Then
        assertThat(passenger).isInstanceOf(EconomyClassPassenger.class);
    }

}