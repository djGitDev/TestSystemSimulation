package ca.uqam.mgl7230.tp3.part2.adapter.flight;

import ca.uqam.mgl7230.tp3.part2.model.flight.FlightInformation;

public interface FlightCatalog {

    FlightInformation getFlightInformation(String flightNumber);
}
