package ca.uqam.mgl7230.tp3.part1.config;

import ca.uqam.mgl7230.tp3.part1.adapter.flight.FlightCatalog;
import ca.uqam.mgl7230.tp3.part1.adapter.flight.impl.FlightCatalogImpl;
import ca.uqam.mgl7230.tp3.part1.adapter.plane.PlaneCatalog;
import ca.uqam.mgl7230.tp3.part1.adapter.plane.impl.PlaneCatalogImpl;
import ca.uqam.mgl7230.tp3.part1.adapter.rest.SavePassengers;
import ca.uqam.mgl7230.tp3.part1.adapter.rest.impl.SavePassengersRestCall;
import ca.uqam.mgl7230.tp3.part1.service.BookingService;
import ca.uqam.mgl7230.tp3.part1.service.FlightPassengerService;
import ca.uqam.mgl7230.tp3.part1.service.PassengerClassService;
import ca.uqam.mgl7230.tp3.part1.service.PassengerService;
import ca.uqam.mgl7230.tp3.part1.service.mapper.FlightBackendRequestMapper;
import ca.uqam.mgl7230.tp3.part1.service.mapper.FlightFrontendRequestMapper;
import ca.uqam.mgl7230.tp3.part1.service.prompt.FlightPromptService;
import ca.uqam.mgl7230.tp3.part1.service.prompt.PassengerPromptService;
import ca.uqam.mgl7230.tp3.part1.utils.DistanceCalculator;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class ApplicationInitializer {

    private static FlightPassengerService flightPassengerService;
    private static FlightPromptService flightPromptService;
    private static PassengerPromptService passengerPromptService;
    private static BookingService bookingService;
    private static PassengerService passengerService;
    private static Scanner scanner;
    private static FlightCatalog flightCatalog;
    private static FlightBackendRequestMapper backendMapper;
    private static FlightFrontendRequestMapper frontendMapper;
    private static SavePassengers savePassengers;

    public static void init() {
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        PlaneCatalog planeCatalog = new PlaneCatalogImpl();
        RestTemplate restTemplate = new RestTemplate();
        PassengerClassService passengerClassService = new PassengerClassService();
        flightCatalog = new FlightCatalogImpl();
        flightPromptService = new FlightPromptService(flightCatalog);
        scanner = new Scanner(System.in);
        passengerService = new PassengerService(distanceCalculator, passengerClassService);
        flightPassengerService = new FlightPassengerService(planeCatalog, flightCatalog);
        bookingService = new BookingService(flightPassengerService, passengerService);
        passengerPromptService = new PassengerPromptService();
        backendMapper = new FlightBackendRequestMapper(passengerClassService);
        frontendMapper = new FlightFrontendRequestMapper(passengerClassService);
        savePassengers = new SavePassengersRestCall(restTemplate);
    }

    public static FlightPassengerService flightPassengerService() {
        return flightPassengerService;
    }

    public static PassengerPromptService passengerPromptService() {
        return passengerPromptService;
    }

    public static BookingService bookingService() {
        return bookingService;
    }

    public static PassengerService passengerService() {
        return passengerService;
    }

    public static FlightCatalog flightCatalog() {
        return flightCatalog;
    }

    public static FlightPromptService flightPromptService() {
        return flightPromptService;
    }

    public static Scanner scanner() {
        return scanner;
    }

    public static FlightBackendRequestMapper backendMapper() {
        return backendMapper;
    }

    public static FlightFrontendRequestMapper frontendMapper() {
        return frontendMapper;
    }

    public static SavePassengers savePassengers() {
        return savePassengers;
    }

}
