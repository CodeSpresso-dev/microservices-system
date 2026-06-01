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
public class CreateCardAPISteps {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CardScenarioContext context;

    @Given("a valid card creation request")
    public void valid_card_creation_request() {

        CreateCardRequest req = CreateCardRequestBuilder.valid();

        context.setRequest(req);
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
