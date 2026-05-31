package ir.mahdi.sample.microservice.cards.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import ir.mahdi.sample.microservice.cards.bdd.builder.CreateCardRequestBuilder;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import ir.mahdi.sample.microservice.cards.exception.InvalidCardTypeException;
import ir.mahdi.sample.microservice.cards.exception.InvalidExpiryDateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class InvalidExpiryDateSteps {

    @Autowired
    private CardScenarioContext context;

    @Given("a valid card creation request with expiry date {string}")
    public void requestWithInvalidCardType(String expiryDate) {

        CreateCardRequest req = CreateCardRequestBuilder.valid();
        req.setExpiryDate(expiryDate);

        context.setRequest(req);
    }

    @Then("InvalidExpiryDateException should be thrown")
    public void thenThrowInvalidExpiryDateException() {
        assertNotNull(context.getException());
        assertThat(context.getException())
                .isInstanceOf(InvalidExpiryDateException.class);
    }
}
