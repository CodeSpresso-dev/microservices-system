package ir.mahdi.sample.microservice.cards.bdd.steps.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.entity.Card;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CreateCardAPIAndPersistDBSteps {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CardScenarioContext context;

    @Then("card should be stored in database")
    public void card_should_be_stored_in_database() throws Exception {

        String responseBody = context.getMvcResult()
                .getResponse()
                .getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(responseBody);

        Long cardId = jsonNode.get("cardId").asLong();

        Card card = cardRepository.findById(cardId)
                .orElse(null);

        assertNotNull(card);

        assertEquals(
                context.getCreateCardRequest().getCardNumber(),
                card.getCardNumber()
        );

        assertEquals(
                context.getCreateCardRequest().getMobileNumber(),
                card.getMobileNumber()
        );

        assertEquals(
                "ACTIVE",
                card.getStatus()
        );
    }
}
