package ca.uqam.mgl7230.tp3.part2.utils;

import ca.uqam.mgl7230.tp3.part2.model.flight.FlightInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DistanceCalculatorTest {

    @InjectMocks
    private DistanceCalculator distanceCalculator;
    @Mock
    private FlightInformation flightInformation;

    @Test
    void getDistanceReturnRealDistance() {
        // Given
        given(flightInformation.getLatSource()).willReturn(123.00);
        given(flightInformation.getLonSource()).willReturn(123.00);
        given(flightInformation.getLatDestination()).willReturn(123.00);
        given(flightInformation.getLonDestination()).willReturn(123.00);

        // When
        double actualDistance = distanceCalculator.calculate(flightInformation);

        // Then
        assertThat(actualDistance).isEqualTo(0.0);
    }

}
