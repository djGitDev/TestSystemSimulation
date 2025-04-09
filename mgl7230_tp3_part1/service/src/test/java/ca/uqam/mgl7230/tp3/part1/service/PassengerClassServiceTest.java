package ca.uqam.mgl7230.tp3.part1.service;

import ca.uqam.mgl7230.tp3.part1.model.passenger.PassengerClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static ca.uqam.mgl7230.tp3.part1.model.passenger.PassengerClass.*;
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

    @Test
    void getFirstFromEconomyClass() {
        // When
        String passengerType = passengerClassService.getPassengerType(FIRST_CLASS);

        // Then
        assertThat(passengerType).isEqualTo("first");
    }

    @Test
    void getBusinessFromBusinessClass() {
        // When
        String passengerType = passengerClassService.getPassengerType(BUSINESS_CLASS);

        // Then
        assertThat(passengerType).isEqualTo("business");
    }

    @Test
    void getEconomyFromEconomyClass() {
        // When
        String passengerType = passengerClassService.getPassengerType(ECONOMY_CLASS);

        // Then
        assertThat(passengerType).isEqualTo("economy");
    }
}
