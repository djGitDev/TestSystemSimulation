//package ca.uqam.mgl7230.tp3.part1.service;
//
//import ca.uqam.mgl7230.tp3.part1.adapter.flight.FlightCatalog;
//import ca.uqam.mgl7230.tp3.part1.adapter.rest.SavePassengers;
//import ca.uqam.mgl7230.tp3.part1.config.ApplicationInitializer;
//import ca.uqam.mgl7230.tp3.part1.model.flight.FlightInformation;
//import ca.uqam.mgl7230.tp3.part1.model.passenger.Passenger;
//import ca.uqam.mgl7230.tp3.part1.service.mapper.FlightFrontendRequestMapper;
//import ca.uqam.mgl7230.tp3.part1.service.prompt.FlightPromptService;
//import ca.uqam.mgl7230.tp3.part1.service.prompt.PassengerPromptService;
//import com.example.model.FlightFrontendPostRequest;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.MockedStatic;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ExecuteServiceTest {
//
//    private static final String FLIGHT_NUMBER = "flightNumber";
//
//    private MockedStatic<ApplicationInitializer> initializeMock;
//    @Mock
//    private FlightPromptService flightPromptService;
//    @Mock
//    private FlightPassengerService flightPassengerService;
//    @Mock
//    private PassengerPromptService passengerPromptService;
//    @Mock
//    private PassengerService passengerService;
//    @Mock
//    private FlightCatalog flightCatalog;
//    @Mock
//    private BookingService bookingService;
//    @Mock
//    private Scanner scanner;
//    @Mock
//    private FlightInformation flightInformation;
//    @Mock
//    private Passenger passenger;
//    @Mock
//    private SavePassengers savePassengers;
//    @Mock
//    private FlightFrontendRequestMapper frontendMapper;
//    @Mock
//    private FlightFrontendPostRequest flightFrontendPostRequest;
//    @Mock
//    private Map passengerData;
//
//
//    @BeforeEach
//    void setup() {
//        given(flightPromptService.getFlightInformation(scanner)).willReturn(flightInformation);
//        given(flightInformation.getFlightNumber()).willReturn(FLIGHT_NUMBER);
//        doNothing().when(flightPassengerService).initializeFlightService(FLIGHT_NUMBER);
//        given(passengerPromptService.getPassengerData(scanner)).willReturn(passengerData);
//        given(frontendMapper.map(FLIGHT_NUMBER, passengerData)).willReturn(flightFrontendPostRequest);
//        given(passengerService.createPassenger(flightInformation, flightFrontendPostRequest)).willReturn(passenger);
//        given(flightCatalog.getFlightInformation(FLIGHT_NUMBER)).willReturn(flightInformation);
//
//        initializeMock = mockStatic(ApplicationInitializer.class);
//        initializeMock.when(ApplicationInitializer::flightPromptService).thenReturn(flightPromptService);
//        initializeMock.when(ApplicationInitializer::flightPassengerService).thenReturn(flightPassengerService);
//        initializeMock.when(ApplicationInitializer::passengerPromptService).thenReturn(passengerPromptService);
//        initializeMock.when(ApplicationInitializer::passengerService).thenReturn(passengerService);
//        initializeMock.when(ApplicationInitializer::flightCatalog).thenReturn(flightCatalog);
//        initializeMock.when(ApplicationInitializer::bookingService).thenReturn(bookingService);
//        initializeMock.when(ApplicationInitializer::scanner).thenReturn(scanner);
//        initializeMock.when(ApplicationInitializer::savePassengers).thenReturn(savePassengers);
//        initializeMock.when(ApplicationInitializer::frontendMapper).thenReturn(frontendMapper);
//    }
//
//    @AfterEach
//    void tearDown() {
//        initializeMock.close();
//    }
//
//    @Test
//    void answerNoLeaveTheLoop() throws JsonProcessingException {
//        // Given
//        given(bookingService.isBooked(passenger, flightInformation, flightFrontendPostRequest)).willReturn(true);
//        FlightFrontendPostRequest FlightFrontendPostRequest = mock(FlightFrontendPostRequest.class);
//        List<FlightFrontendPostRequest> flightFrontendPostRequest = List.of(FlightFrontendPostRequest);
//        given(savePassengers.call(flightFrontendPostRequest)).willReturn(null);
//        given(frontendMapper.map(FLIGHT_NUMBER, passengerData)).willReturn(flightFrontendPostRequest.getFirst());
//        given(scanner.nextLine()).willReturn("no");
//        doNothing().when(scanner).close();
//
//        // When
//        ExecuteService.execute();
//
//        // Then
//        verify(scanner).close();
//        verify(savePassengers).call(flightFrontendPostRequest);
//    }
//
//    @Test
//    void answerYesOnceAndNoToLeaveTheLoop() throws JsonProcessingException {
//        // Given
//        List<FlightFrontendPostRequest> flightFrontendPostRequests = List.of();
//        given(bookingService.isBooked(passenger, flightInformation, flightFrontendPostRequest)).willReturn(false);
//        given(savePassengers.call(flightFrontendPostRequests)).willReturn(null);
//        given(scanner.nextLine()).willReturn("yes").willReturn("no");
//        doNothing().when(scanner).close();
//
//        // When
//        ExecuteService.execute();
//
//        // Then
//        verify(scanner).close();
//    }
//
//}