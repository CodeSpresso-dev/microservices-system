package ir.mahdi.sample.microservice.cards.bdd.steps.api;

import io.cucumber.java.en.Given;
import ir.mahdi.sample.microservice.cards.bdd.builder.CreateCardRequestBuilder;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardApiValidationInvalidCVV {

    @Autowired
    private CardScenarioContext context;


    @Given("a valid card creation request with CVV {string}")
    public void valid_card_creation_request_with_CVV_(String cvv) {

        CreateCardRequest req = CreateCardRequestBuilder.valid();
        req.setCvv(cvv);

        context.setCreateCardRequest(req);
    }

}
