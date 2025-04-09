package ca.uqam.mgl7230.tp3.part2.integration;

import ca.uqam.mgl7230.tp3.part2.Application;
import ca.uqam.mgl7230.tp3.part2.adapter.flight.FlightCatalog;
import ca.uqam.mgl7230.tp3.part2.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp3.part2.model.passenger.PassengerClass;
import ca.uqam.mgl7230.tp3.part2.model.plane.PlaneType;
import ca.uqam.mgl7230.tp3.part2.rest.FlightBookingController;
import com.example.model.FlightBackendPostRequest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static ca.uqam.mgl7230.tp3.part2.integration.support.HttpExchangeSupport.buildHttpExchange;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class FlightTestDefinition {

    private String passengerPassport;
    private String passengerName;
    private int passengerAge;
    private String passengerType;
    private PassengerClass expectedPassengerType;
    private String flightNumber;
    private FlightCatalog mockFlightCatalog;

    @Before
    public void setup() {
        mockFlightCatalog = mock(FlightCatalog.class);
    }

    @After
    public void tearDown() {
        File file = new File("passengerData.csv");
        boolean delete = file.delete();
        if (!delete) {
            fail();
        }
    }

    @Given("passenger selects flight {string}")
    public void passengerSelectsFlight(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Given("flight catalog doesn't have this flight")
    public void flightCatalogDoesnTHaveThisFlight() {
        given(mockFlightCatalog.getFlightInformation(flightNumber)).willReturn(null);
    }

    @Given("flight catalog is mocked")
    public void flightCatalogIsMocked() {
        given(mockFlightCatalog.getFlightInformation(flightNumber))
                .willReturn(new FlightInformation(
                        flightNumber, 45.508888, -73.561668, -23.533773, -46.625290,
                        PlaneType.BOEING));
    }

    @Given("a passenger with passport number {string}")
    public void aPassengerWithPassportNumber(String passportNumber) {
        this.passengerPassport = passportNumber;
    }

    @Given("name {string}")
    public void name(String name) {
        this.passengerName = name;
    }

    @Given("age {int}")
    public void age(int age) {
        this.passengerAge = age;
    }

    @Given("looking to buy in {string} class")
    public void lookingToBuyInClass(String passengerClass) {
        this.passengerType = passengerClass;
        switch (passengerClass) {
            case "first" -> expectedPassengerType = PassengerClass.FIRST_CLASS;
            case "business" -> expectedPassengerType = PassengerClass.BUSINESS_CLASS;
            case "economy" -> expectedPassengerType = PassengerClass.ECONOMY_CLASS;
        }
    }

    @When("service receives a call")
    public void passengerSelectsFlight2() throws IOException {
        FlightBackendPostRequest flightPostRequest = new FlightBackendPostRequest();
        flightPostRequest.setFlightNumber(flightNumber);
        flightPostRequest.setPassengerAge(passengerAge);
        flightPostRequest.setPassengerName(passengerName);
        flightPostRequest.setPassengerPassport(passengerPassport);
        flightPostRequest.setPassengerType(passengerType);

        Application.main(null);
        new FlightBookingController().handle(buildHttpExchange(flightPostRequest));
    }

    @Then("passenger is added to fly {string}")
    public void passengerIsAddedToFly(String flightNumber) {
        String filePath = "passengerData.csv"; // Replace with the actual path to your file

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder actualLine = new StringBuilder();
            br.lines().forEach(actualLine::append);
            assertThat(actualLine).contains(flightNumber);
            assertThat(actualLine).contains(passengerPassport);
            assertThat(actualLine).contains(passengerName);
            assertThat(actualLine).contains(String.valueOf(passengerAge));
            assertThat(actualLine).contains(expectedPassengerType.name());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

}