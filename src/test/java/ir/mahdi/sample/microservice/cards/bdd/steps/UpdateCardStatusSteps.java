package ir.mahdi.sample.microservice.cards.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.mahdi.sample.microservice.cards.bdd.builder.CardBuilder;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.entity.Card;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import ir.mahdi.sample.microservice.cards.service.CardService;
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

    @Autowired
    private CardService cardService;

    @Given("I request to create a card with status {string}")
    public void create_a_card_with_status_(String status) {

        Card card = CardBuilder.DefaultCard();
        card.setStatus(status);

        Card savedCard = cardRepository.save(card);

        context.setCardId(savedCard.getId());
    }

    @When("I update card status to {string}")
    public void update_card_status_to_(String updatedStatus) {
        context.setResponse(
                cardService.updateStatus(context.getCardId(), updatedStatus)
        );
    }

    @Then("card status should be {string}")
    public void card_status_should_be_(String expectedStatus) {

        assertNotNull(context.getResponse());

        assertEquals(
                expectedStatus,
                context.getResponse().getStatus()
        );
    }
}
