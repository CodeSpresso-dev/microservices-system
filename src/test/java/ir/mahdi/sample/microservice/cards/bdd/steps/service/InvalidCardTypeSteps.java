package ir.mahdi.sample.microservice.cards.bdd.steps.service;

import io.cucumber.java.en.Given;
import ir.mahdi.sample.microservice.cards.bdd.builder.CreateCardRequestBuilder;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
