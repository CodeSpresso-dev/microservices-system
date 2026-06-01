package ir.mahdi.sample.microservice.cards.bdd.steps.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.helper.ApiResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class CardApiSteps {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CardScenarioContext context;

    @When("I send POST request to {string}")
    public void send_post_request(String url) {

        try {

            String requestBody =
                    objectMapper.writeValueAsString(
                            context.getCreateCardRequest()
                    );

            MvcResult mvcResult =
                    mockMvc.perform(
                            post(url)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody)
                    ).andReturn();

            context.setMvcResult(mvcResult);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @When("I send GET request to {string}")
    public void send_get_request(String url) throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                        get(url)
                                .contentType(MediaType.APPLICATION_JSON)
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

    @Then("api response should contain validation error code {string}")
    public void response_should_contain_validation_error_message(String errorCode)
            throws Exception {

        ApiResponseHelper.assertThat(
                        context.getMvcResult()
                )
                .hasStatus(400)
                .isFailure()
                .hasErrorCode(errorCode);
    }
}
