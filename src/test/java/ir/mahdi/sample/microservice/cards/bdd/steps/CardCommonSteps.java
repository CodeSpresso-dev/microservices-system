package ir.mahdi.sample.microservice.cards.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import ir.mahdi.sample.microservice.cards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardCommonSteps {

    @Autowired
    private CardScenarioContext context;

    @Autowired
    private CardService cardService;

    @When("I request to create a card")
    public void create_card() {

        try {
            context.setResponse(cardService.createCard(context.getRequest()));
        } catch (Exception ex) {
            context.setException(ex);
        }
    }

}
