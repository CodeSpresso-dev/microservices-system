package ir.mahdi.sample.microservice.cards.bdd.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Then("{string} should be thrown")
    public void exception_should_be_thrown(String exceptionName) {

        assertNotNull(context.getException());

        assertEquals(
                exceptionName,
                context.getException().getClass().getSimpleName()
        );
    }


}
