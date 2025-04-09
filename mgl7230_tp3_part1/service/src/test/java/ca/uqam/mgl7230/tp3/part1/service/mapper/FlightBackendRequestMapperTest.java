package ca.uqam.mgl7230.tp3.part1.service.mapper;

import ca.uqam.mgl7230.tp3.part1.model.passenger.*;
import ca.uqam.mgl7230.tp3.part1.service.PassengerClassService;
import com.example.model.FlightFrontendPostRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FlightBackendRequestMapperTest {

    @InjectMocks
    private FlightBackendRequestMapper FlightFrontendPostRequestMapper;
    @Mock
    private PassengerClassService passengerClassService;

    @Test
    void mapFirst() {
        //Given
        String flightNumber = "flightNumber";
        String passport = "passport";
        String name = "name";
        int age = 10;
        int millage = 1000;
        String passengerType = "first";
        Passenger passenger = new FirstClassPassenger(passport, name, age, millage);
        given(passengerClassService.getPassengerType(PassengerClass.FIRST_CLASS)).willReturn(passengerType);

        // When
        FlightFrontendPostRequest FlightFrontendPostRequest = FlightFrontendPostRequestMapper.map(flightNumber, passenger);

        // Then
        assertThat(FlightFrontendPostRequest.getFlightNumber()).isEqualTo(flightNumber);
        assertThat(FlightFrontendPostRequest.getPassengerPassport()).isEqualTo(passport);
        assertThat(FlightFrontendPostRequest.getPassengerName()).isEqualTo(name);
        assertThat(FlightFrontendPostRequest.getPassengerAge()).isEqualTo(age);
        assertThat(FlightFrontendPostRequest.getPassengerType()).isEqualTo(passengerType);
    }

}