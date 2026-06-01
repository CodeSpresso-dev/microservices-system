package ir.mahdi.sample.microservice.cards.bdd.steps.service;

import io.cucumber.java.en.When;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import ir.mahdi.sample.microservice.cards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateNonExistingCardStatusSteps {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    @Autowired
    private CardScenarioContext context;

    @When("I update card status to BLOCKED")
    public void update_card_status_to_blocked() {
        try {
            cardService.updateStatus(context.getCardId(), "BLOCKED");
        } catch (Exception e) {
            context.setException(e);
        }
    }

}
