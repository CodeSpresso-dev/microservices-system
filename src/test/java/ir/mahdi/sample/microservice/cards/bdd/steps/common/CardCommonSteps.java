package ir.mahdi.sample.microservice.cards.bdd.steps.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.entity.Card;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import ir.mahdi.sample.microservice.cards.service.CardService;
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
public class CardCommonSteps {

    private final String baseUrl = "http://localhost:8080";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CardScenarioContext context;

    @Autowired
    private CardService cardService;

    @Autowired
    private CardRepository cardRepository;

    @Given("a card exists")
    public void a_card_exists() {

        Card card = Card.builder()
                .mobileNumber("09123456789")
                .cardHolderName("Mehdi")
                .cardNumber("55555")
                .cvv("123")
                .expiryDate("202605")
                .cardType("DEBIT")
                .status("ACTIVE")
                .build();

        Card saved = cardRepository.save(card);

        context.setCardId(saved.getId());
    }

    @Given("card with id {long} does not exist")
    public void card_with_id_does_not_exist(Long id) {

        context.setCardId(id);
        cardRepository.deleteById(id);
    }

    @When("I request to create a card")
    public void create_card() {

        try {
            context.setResponse(cardService.createCard(context.getRequest()));
        } catch (Exception ex) {
            context.setException(ex);
        }
    }

    @When("I update card status to {string}")
    public void update_card_status_to_(String updatedStatus) {
        try {
            context.setResponse(
                    cardService.updateStatus(context.getCardId(), updatedStatus)
            );
        } catch (Exception e) {
            context.setException(e);
        }

    }

    @Then("{string} should be thrown")
    public void exception_should_be_thrown(String exceptionName) {

        assertNotNull(context.getException());

        assertEquals(
                exceptionName,
                context.getException().getClass().getSimpleName()
        );
    }


    // =========================
    // 2. API SIDE
    // =========================
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


}
