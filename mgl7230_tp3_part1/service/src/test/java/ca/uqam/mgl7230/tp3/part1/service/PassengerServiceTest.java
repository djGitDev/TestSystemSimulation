package ca.uqam.mgl7230.tp3.part1.service;

import ca.uqam.mgl7230.tp3.part1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp3.part1.model.passenger.*;
import ca.uqam.mgl7230.tp3.part1.utils.DistanceCalculator;
import com.example.model.FlightFrontendPostRequest;
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
    private FlightFrontendPostRequest flightFrontendPostRequest;
    @Mock
    private PassengerClassService passengerClassService;

    @BeforeEach
    void setup() {
        given(flightFrontendPostRequest.getPassengerPassport()).willReturn(PASSENGER_PASSPORT);
        given(flightFrontendPostRequest.getPassengerName()).willReturn(PASSENGER_NAME);
        given(flightFrontendPostRequest.getPassengerAge()).willReturn(PASSENGER_AGE);
    }

    @Test
    void createFirstClassPassenger() {
        // Given
        given(flightFrontendPostRequest.getPassengerType()).willReturn("first");
        given(passengerClassService.getPassengerClass("first")).willReturn(PassengerClass.FIRST_CLASS);
        given(distanceCalculator.calculate(flightInformation)).willReturn(0);

        // When
        Passenger passenger = passengerService.createPassenger(flightInformation, flightFrontendPostRequest);

        // Then
        assertThat(passenger).isInstanceOf(FirstClassPassenger.class);
    }

    @Test
    void createBusinessClassPassenger() {
        // Given
        given(flightFrontendPostRequest.getPassengerType()).willReturn("business");
        given(passengerClassService.getPassengerClass("business")).willReturn(PassengerClass.BUSINESS_CLASS);
        given(distanceCalculator.calculate(flightInformation)).willReturn(0);

        // When
        Passenger passenger = passengerService.createPassenger(flightInformation, flightFrontendPostRequest);

        // Then
        assertThat(passenger).isInstanceOf(BusinessClassPassenger.class);
    }

    @Test
    void createEconomyClassPassenger() {
        // Given
        given(flightFrontendPostRequest.getPassengerType()).willReturn("economy");
        given(passengerClassService.getPassengerClass("economy")).willReturn(PassengerClass.ECONOMY_CLASS);
        given(distanceCalculator.calculate(flightInformation)).willReturn(0);

        // When
        Passenger passenger = passengerService.createPassenger(flightInformation, flightFrontendPostRequest);

        // Then
        assertThat(passenger).isInstanceOf(EconomyClassPassenger.class);
    }

}