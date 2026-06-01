package ir.mahdi.sample.microservice.cards.bdd.steps.common;

import io.cucumber.java.en.When;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardServiceSteps {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardScenarioContext context;

    @When("I request to create a card")
    public void create_card() {

        try {
            context.setCardResponse(
                    cardService.createCard(
                            context.getCreateCardRequest()
                    )
            );
        } catch (Exception ex) {
            context.setException(ex);
        }
    }

    @When("I update card status to {string}")
    public void update_card_status_to(String updatedStatus) {

        try {
            context.setCardResponse(
                    cardService.updateStatus(
                            context.getCardId(),
                            updatedStatus
                    )
            );
        } catch (Exception ex) {
            context.setException(ex);
        }
    }
}
