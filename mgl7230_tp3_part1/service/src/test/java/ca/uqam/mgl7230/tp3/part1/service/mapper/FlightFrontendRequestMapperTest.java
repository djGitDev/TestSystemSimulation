package ca.uqam.mgl7230.tp3.part1.service.mapper;

import ca.uqam.mgl7230.tp3.part1.model.passenger.PassengerKeyConstants;
import ca.uqam.mgl7230.tp3.part1.service.PassengerClassService;
import com.example.model.FlightFrontendPostRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static ca.uqam.mgl7230.tp3.part1.model.passenger.PassengerClass.FIRST_CLASS;
import static ca.uqam.mgl7230.tp3.part1.model.passenger.PassengerKeyConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FlightFrontendRequestMapperTest {

    @InjectMocks
    private FlightFrontendRequestMapper flightFrontendRequestMapper;
    @Mock
    private PassengerClassService passengerClassService;

    @Test
    void mapFirst() {
        // Given
        String flightNumber = "flightNumber";
        String passport = "passport";
        String name = "name";
        int age = 10;
        String passengerType = "first";
        Map<PassengerKeyConstants, Object> passengerMap = Map.of(PASSENGER_PASSPORT, passport,
                PASSENGER_NAME, name,
                PASSENGER_AGE, age,
                PASSENGER_CLASS, FIRST_CLASS);
        given(passengerClassService.getPassengerType(FIRST_CLASS)).willReturn(passengerType);

        // When
        FlightFrontendPostRequest actual = flightFrontendRequestMapper.map(flightNumber, passengerMap);

        // Then
        assertThat(actual.getFlightNumber()).isEqualTo(flightNumber);
        assertThat(actual.getPassengerPassport()).isEqualTo(passport);
        assertThat(actual.getPassengerName()).isEqualTo(name);
        assertThat(actual.getPassengerAge()).isEqualTo(age);
        assertThat(actual.getPassengerType()).isEqualTo(passengerType);
    }

}