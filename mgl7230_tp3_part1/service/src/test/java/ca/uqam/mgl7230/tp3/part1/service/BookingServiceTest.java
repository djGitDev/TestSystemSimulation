package ca.uqam.mgl7230.tp3.part1.service;

import ca.uqam.mgl7230.tp3.part1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp3.part1.model.passenger.Passenger;
import ca.uqam.mgl7230.tp3.part1.model.passenger.PassengerClass;
import com.example.model.FlightFrontendPostRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;
    @Mock
    private FlightPassengerService flightPassengerService;
    @Mock
    private Passenger firstClassPassenger;
    @Mock
    private Passenger businessClassPassenger;
    @Mock
    private Passenger economyClassPassenger;
    @Mock
    private PassengerService passengerService;
    @Mock
    private FlightInformation flightInformation;
    @Mock
    private FlightFrontendPostRequest flightFrontendPostRequest;

    @Test
    void successfullyAddFirstClassPassengerDirectly() {
        // Given
        given(firstClassPassenger.getType()).willReturn(PassengerClass.FIRST_CLASS);
        given(flightPassengerService.numberOfTotalSeatsAvailable()).willReturn(1);
        given(flightPassengerService.numberOfFirstClassSeatsAvailable()).willReturn(1);

        // When
        bookingService.isBooked(firstClassPassenger, flightInformation, flightFrontendPostRequest);

        // Then
        verify(flightPassengerService, never()).numberOfBusinessClassSeatsAvailable();
        verify(flightPassengerService, never()).numberOfEconomyClassSeatsAvailable();
        verify(flightPassengerService).addPassenger(firstClassPassenger);
    }

    @Test
    void successfullyAddBusinessClassPassengerDirectly() {
        // Given
        given(businessClassPassenger.getType()).willReturn(PassengerClass.BUSINESS_CLASS);
        given(flightPassengerService.numberOfTotalSeatsAvailable()).willReturn(1);
        given(flightPassengerService.numberOfBusinessClassSeatsAvailable()).willReturn(1);

        // When
        bookingService.isBooked(businessClassPassenger, flightInformation, flightFrontendPostRequest);

        // Then
        verify(flightPassengerService, never()).numberOfFirstClassSeatsAvailable();
        verify(flightPassengerService, never()).numberOfEconomyClassSeatsAvailable();
        verify(flightPassengerService).addPassenger(businessClassPassenger);
    }

    @Test
    void successfullyAddEconomyClassPassengerDirectly() {
        // Given
        given(economyClassPassenger.getType()).willReturn(PassengerClass.ECONOMY_CLASS);
        given(flightPassengerService.numberOfTotalSeatsAvailable()).willReturn(1);
        given(flightPassengerService.numberOfEconomyClassSeatsAvailable()).willReturn(1);

        // When
        bookingService.isBooked(economyClassPassenger, flightInformation, flightFrontendPostRequest);

        // Then
        verify(flightPassengerService, never()).numberOfFirstClassSeatsAvailable();
        verify(flightPassengerService, never()).numberOfBusinessClassSeatsAvailable();
        verify(flightPassengerService).addPassenger(economyClassPassenger);
    }

    @Test
    void tryFirstClassAndAddBusinessClassPassenger() {
        // Given
        given(firstClassPassenger.getType()).willReturn(PassengerClass.FIRST_CLASS);
        given(flightPassengerService.numberOfTotalSeatsAvailable()).willReturn(1);
        given(flightPassengerService.numberOfFirstClassSeatsAvailable()).willReturn(0);
        given(passengerService.createPassenger(flightInformation,
                flightFrontendPostRequest)).willReturn(businessClassPassenger);
        given(businessClassPassenger.getType()).willReturn(PassengerClass.BUSINESS_CLASS);
        given(flightPassengerService.numberOfBusinessClassSeatsAvailable()).willReturn(1);

        // When
        bookingService.isBooked(firstClassPassenger, flightInformation, flightFrontendPostRequest);

        // Then
        verify(flightPassengerService, never()).numberOfEconomyClassSeatsAvailable();
        verify(flightPassengerService).addPassenger(businessClassPassenger);
    }

    @Test
    void tryFirstClassAndThenBusinessClassAndFinallyAddEconomyPassenger() {
        // Given
        given(firstClassPassenger.getType()).willReturn(PassengerClass.FIRST_CLASS);
        given(flightPassengerService.numberOfTotalSeatsAvailable()).willReturn(1);
        given(flightPassengerService.numberOfFirstClassSeatsAvailable()).willReturn(0);
        given(passengerService.createPassenger(flightInformation,
                flightFrontendPostRequest)).willReturn(businessClassPassenger);
        given(passengerService.createPassenger(flightInformation,
                flightFrontendPostRequest)).willReturn(economyClassPassenger);
        given(flightPassengerService.numberOfEconomyClassSeatsAvailable()).willReturn(1);
        given(economyClassPassenger.getType()).willReturn(PassengerClass.ECONOMY_CLASS);

        // When
        bookingService.isBooked(firstClassPassenger, flightInformation, flightFrontendPostRequest);

        // Then
        verify(flightPassengerService).addPassenger(economyClassPassenger);
    }

    @Test
    void tryFirstClassAndThenBusinessClassAndThenEconomyClassButFlightIsFull() {
        // Given
        given(firstClassPassenger.getType()).willReturn(PassengerClass.FIRST_CLASS);
        given(flightPassengerService.numberOfTotalSeatsAvailable()).willReturn(1);
        given(flightPassengerService.numberOfFirstClassSeatsAvailable()).willReturn(0);
        given(passengerService.createPassenger(flightInformation,
                flightFrontendPostRequest)).willReturn(businessClassPassenger);
        given(passengerService.createPassenger(flightInformation,
                flightFrontendPostRequest)).willReturn(economyClassPassenger);
        given(flightPassengerService.numberOfEconomyClassSeatsAvailable()).willReturn(0);
        given(economyClassPassenger.getType()).willReturn(PassengerClass.ECONOMY_CLASS);

        // When
        bookingService.isBooked(firstClassPassenger, flightInformation, flightFrontendPostRequest);

        // Then
        verify(flightPassengerService, never()).addPassenger(economyClassPassenger);
    }

    @Test
    void addPassengerNeverCalled() {
        // Given
        given(flightPassengerService.numberOfTotalSeatsAvailable()).willReturn(0);

        // When
        bookingService.isBooked(firstClassPassenger, flightInformation, flightFrontendPostRequest);

        // Then
        verify(flightPassengerService, never()).numberOfFirstClassSeatsAvailable();
        verify(flightPassengerService, never()).numberOfBusinessClassSeatsAvailable();
        verify(flightPassengerService, never()).numberOfEconomyClassSeatsAvailable();
        verify(flightPassengerService, never()).addPassenger(firstClassPassenger);
    }
}