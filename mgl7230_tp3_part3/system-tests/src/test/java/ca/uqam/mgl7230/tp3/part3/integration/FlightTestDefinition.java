package ca.uqam.mgl7230.tp3.part3.integration;

import ca.uqam.mgl7230.tp3.part3.support.SystemCall;
import com.example.model.FlightPostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FlightTestDefinition {

    private SystemCall systemCall;
    private ObjectMapper mapper;
    private ArrayNode arrayNode;
    private ResponseEntity<String> systemCallResult;
    private int status;
    private List<FlightPostRequest> flightPostRequests;

    @Before
    public void setup() {
        systemCall = new SystemCall(new RestTemplate());
        mapper = new ObjectMapper();
    }

    @After
    public void tearDown() {
    }

    @Given("the following information")
    public void theFollowingInformation(String payload) throws JsonProcessingException {
        arrayNode = (ArrayNode) mapper.readTree(payload);
        flightPostRequests = new ArrayList<>();
        for (JsonNode node : arrayNode) {
            FlightPostRequest request = mapper.treeToValue(node, FlightPostRequest.class);
            flightPostRequests.add(request);
        }
    }

    @When("call to save passengers is launched")
    public void callToSavePassengersIsLaunched() {
        try {
            systemCallResult = systemCall.call(arrayNode);
            //systemCallResult = systemCall.call(flightPostRequests);
            status = systemCallResult.getStatusCode().value();
        } catch (HttpClientErrorException e) {
            status = e.getStatusCode().value();
        }
    }

    @Then("response code is equal to {int}")
    public void responseCodeIsEqualTo(int status) {
        assertThat(this.status).isEqualTo(status);
    }

    @Then("following data is in the saved file")
    public void followingDataIsInTheSavedFile(List<String> expectedValues) throws IOException {
        String userDir = System.getProperty("user.dir");
        Path filePath = Paths.get(userDir + "/../../mgl7230_tp3_part2/service/passengerData" + systemCallResult.getBody() + ".csv");
        List<String> actualList = Files.readAllLines(filePath);
        assertThat(actualList).isEqualTo(expectedValues);
    }

    @Then("following data is not in the saved file")
    public void followingDataIsNotInTheSavedFile(List<String> expectedValues) throws IOException {
        String userDir = System.getProperty("user.dir");
        Path filePath = Paths.get(userDir + "/../../mgl7230_tp3_part2/service/passengerData" + systemCallResult.getBody() + ".csv");
        List<String> actualList = Files.readAllLines(filePath);
        for (String line : expectedValues) {
            assertThat(actualList).doesNotContain(line);
        }
    }
    @Then("no saved file created")
    public void noCreatedFileReturnedInResponse() throws IOException {
            assertThat(systemCallResult).isNull();
    }

}
