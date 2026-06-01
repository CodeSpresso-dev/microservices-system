package ir.mahdi.sample.microservice.cards.bdd.steps.service;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import ir.mahdi.sample.microservice.cards.bdd.builder.CardBuilder;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.entity.Card;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UpdateCardStatusSteps {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardScenarioContext context;

    @Given("I request to create a card with status {string}")
    public void create_a_card_with_status_(String status) {

        Card card = CardBuilder.DefaultCard();
        card.setStatus(status);

        Card savedCard = cardRepository.save(card);

        context.setCardId(savedCard.getId());
    }

    @Then("card status should be {string}")
    public void card_status_should_be_(String expectedStatus) {

        assertNotNull(context.getCardResponse());

        assertEquals(
                expectedStatus,
                context.getCardResponse().getStatus()
        );
    }
}
