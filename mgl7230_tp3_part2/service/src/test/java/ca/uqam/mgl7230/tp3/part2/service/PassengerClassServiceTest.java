package ca.uqam.mgl7230.tp3.part2.service;

import ca.uqam.mgl7230.tp3.part2.model.passenger.PassengerClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static ca.uqam.mgl7230.tp3.part2.model.passenger.PassengerClass.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PassengerClassServiceTest {

    @InjectMocks
    private PassengerClassService passengerClassService;

    @Test
    void getPassengerClassFirstClass() {
        // When
        PassengerClass passengerClass = passengerClassService.getPassengerClass("first");

        // Then
        assertThat(passengerClass).isEqualTo(FIRST_CLASS);
    }

    @Test
    void getPassengerClassBusinessClass() {
        // When
        PassengerClass passengerClass = passengerClassService.getPassengerClass("business");

        // Then
        assertThat(passengerClass).isEqualTo(BUSINESS_CLASS);
    }

    @Test
    void getPassengerClassEconomyClass() {
        // When
        PassengerClass passengerClass = passengerClassService.getPassengerClass("economy");

        // Then
        assertThat(passengerClass).isEqualTo(ECONOMY_CLASS);
    }
}
