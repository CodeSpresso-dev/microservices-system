package ir.mahdi.sample.microservice.cards.bdd.steps.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.mahdi.sample.microservice.cards.bdd.builder.CreateCardRequestBuilder;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateCardAPISteps {

    private String baseUrl = "http://localhost:8080";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CardScenarioContext context;

    @Given("a valid card creation request")
    public void valid_card_creation_request() {

        CreateCardRequest req = CreateCardRequestBuilder.valid();

        context.setRequest(req);
    }

    @When("I send POST request to {string}")
    public void send_post_request(String url)
            throws Exception {

        url = baseUrl + url;

        String requestBody =
                objectMapper.writeValueAsString(
                        context.getRequest()
                );

        MvcResult mvcResult =
                mockMvc.perform(
                                post(url)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(requestBody)
                        )
                        .andReturn();

        context.setMvcResult(mvcResult);
    }

    @Then("api response status should be {int}")
    public void response_status_should_be(Integer expectedStatus) {

        assertEquals(
                expectedStatus,
                context.getMvcResult()
                        .getResponse()
                        .getStatus()
        );
    }

    @Then("api response should contain cardId")
    public void response_should_contain_card_id()
            throws Exception {

        String responseBody =
                context.getMvcResult()
                        .getResponse()
                        .getContentAsString();

        JsonNode jsonNode =
                objectMapper.readTree(responseBody);

        assertNotNull(jsonNode.get("cardId"));
    }
}
