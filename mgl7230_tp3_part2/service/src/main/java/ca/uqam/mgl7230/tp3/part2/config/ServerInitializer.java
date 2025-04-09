package ca.uqam.mgl7230.tp3.part2.config;

import ca.uqam.mgl7230.tp3.part2.adapter.flight.FlightCatalog;
import ca.uqam.mgl7230.tp3.part2.adapter.flight.impl.FlightCatalogImpl;
import ca.uqam.mgl7230.tp3.part2.adapter.persist.SavePassengerInFlight;
import ca.uqam.mgl7230.tp3.part2.service.PassengerClassService;
import ca.uqam.mgl7230.tp3.part2.service.PassengerService;
import ca.uqam.mgl7230.tp3.part2.utils.DistanceCalculator;

import java.io.IOException;

public class ServerInitializer {

    private static PassengerService passengerService;
    private static SavePassengerInFlight savePassengerInFlight;
    private static FlightCatalog flightCatalog;
    private static FileWriterProvider fileWriterProvider;

    public static void initServer() throws IOException {
        HttpServerStarter httpServerStarter = new HttpServerStarter();
        httpServerStarter.startServer();
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        fileWriterProvider = new FileWriterProvider();
        flightCatalog = new FlightCatalogImpl();
        savePassengerInFlight = new SavePassengerInFlight();
        PassengerClassService passengerClassService = new PassengerClassService();
        passengerService = new PassengerService(distanceCalculator, passengerClassService);
    }

    public static PassengerService passengerService() {
        return passengerService;
    }

    public static SavePassengerInFlight savePassengerInFlight() {
        return savePassengerInFlight;
    }

    public static FlightCatalog flightCatalog() {
        return flightCatalog;
    }

    public static FileWriterProvider fileWriterProvider() {
        return fileWriterProvider;
    }
}
