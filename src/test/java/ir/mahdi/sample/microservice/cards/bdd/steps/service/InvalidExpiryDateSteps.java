package ir.mahdi.sample.microservice.cards.bdd.steps.service;

import io.cucumber.java.en.Given;
import ir.mahdi.sample.microservice.cards.bdd.builder.CreateCardRequestBuilder;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvalidExpiryDateSteps {

    @Autowired
    private CardScenarioContext context;

    @Given("a valid card creation request with expiry date {string}")
    public void requestWithInvalidCardType(String expiryDate) {

        CreateCardRequest req = CreateCardRequestBuilder.valid();
        req.setExpiryDate(expiryDate);

        context.setCreateCardRequest(req);
    }
}
