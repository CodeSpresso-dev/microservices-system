package ir.mahdi.sample.microservice.cards.bdd.steps.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import ir.mahdi.sample.microservice.cards.bdd.builder.CardBuilder;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.entity.Card;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GetCardsByMobileNumber {

    @Autowired
    private CardScenarioContext context;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Given("cards exist for mobile number {string}")
    public void card_exist_for_mobile_number_(String mobileNumber) {

        Card card1 = CardBuilder.DefaultCard();
        card1.setMobileNumber(mobileNumber);
        card1.setCardNumber("1234");
        cardRepository.saveAndFlush(card1);

        Card card2 = CardBuilder.DefaultCard();
        card2.setMobileNumber(mobileNumber);
        card2.setCardNumber("12345");
        cardRepository.saveAndFlush(card2);
    }

    @Then("api response should contain {int} cards")
    public void response_should_contain_cards(Integer expectedCount)
            throws Exception {

        String responseBody =
                context.getMvcResult()
                        .getResponse()
                        .getContentAsString();

        JsonNode jsonNode =
                objectMapper.readTree(responseBody);

        assertTrue(jsonNode.isArray());

        assertEquals(
                expectedCount,
                jsonNode.size()
        );
    }
}
