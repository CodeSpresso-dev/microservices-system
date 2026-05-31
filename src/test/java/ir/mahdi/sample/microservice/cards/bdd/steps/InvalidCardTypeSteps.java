package ir.mahdi.sample.microservice.cards.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import ir.mahdi.sample.microservice.cards.bdd.builder.CreateCardRequestBuilder;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import ir.mahdi.sample.microservice.cards.exception.CardAlreadyExistsException;
import ir.mahdi.sample.microservice.cards.exception.InvalidCardTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class InvalidCardTypeSteps {

    @Autowired
    private CardScenarioContext context;

    @Given("a valid card creation request with card type {string}")
    public void requestWithInvalidCardType(String cardType) {

        CreateCardRequest req = CreateCardRequestBuilder.valid();
        req.setCardType(cardType);

        context.setRequest(req);
    }

    @Then("InvalidCardTypeException should be thrown")
    public void thenThrowInvalidCardTypeException() {
        assertNotNull(context.getException());
        assertThat(context.getException())
                .isInstanceOf(InvalidCardTypeException.class);
    }
}
