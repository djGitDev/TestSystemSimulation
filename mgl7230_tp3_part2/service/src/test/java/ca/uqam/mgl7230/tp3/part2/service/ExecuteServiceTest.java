package ca.uqam.mgl7230.tp3.part2.service;

import ca.uqam.mgl7230.tp3.part2.adapter.flight.FlightCatalog;
import ca.uqam.mgl7230.tp3.part2.adapter.persist.SavePassengerInFlight;
import ca.uqam.mgl7230.tp3.part2.config.ServerInitializer;
import ca.uqam.mgl7230.tp3.part2.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp3.part2.model.passenger.Passenger;
import com.example.model.FlightBackendPostRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileWriter;

import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class ExecuteServiceTest {

    private static final String FLIGHT_NUMBER = "flightNumber";

    private MockedStatic<ServerInitializer> initializeMock;
    @Mock
    private PassengerService passengerService;
    @Mock
    private FlightCatalog flightCatalog;
    @Mock
    private SavePassengerInFlight savePassengerInFlight;
    @Mock
    private FileWriter fileWriter;
    @Mock
    private FlightInformation flightInformation;
    @Mock
    private Passenger passenger;
    @Mock
    private FlightBackendPostRequest flightPostRequest;

    @BeforeEach
    void setup() {
        initializeMock = mockStatic(ServerInitializer.class);
        initializeMock.when(ServerInitializer::flightCatalog).thenReturn(flightCatalog);
        initializeMock.when(ServerInitializer::passengerService).thenReturn(passengerService);
        initializeMock.when(ServerInitializer::savePassengerInFlight).thenReturn(savePassengerInFlight);
        initializeMock.when(ServerInitializer::fileWriterProvider).thenReturn(fileWriter);
    }

    @AfterEach
    void tearDown() {
        initializeMock.close();
    }

//    @Test
//    void answerNoLeaveTheLoop() throws IOException {
//        // Given
//        given(flightPostRequest.getFlightNumber()).willReturn(FLIGHT_NUMBER);
//        given(flightCatalog.getFlightInformation(FLIGHT_NUMBER)).willReturn(flightInformation);
//        given(passengerService.createPassenger(flightInformation, flightPostRequest)).willReturn(passenger);
//
//        // When
//        ExecuteService.execute(flightPostRequest);
//
//        // Then
//        verify(savePassengerInFlight).save(fileWriter, passenger, FLIGHT_NUMBER);
//    }

}