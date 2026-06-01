package ir.mahdi.sample.microservice.cards.bdd.steps.api;

import io.cucumber.java.en.Given;
import ir.mahdi.sample.microservice.cards.bdd.builder.CreateCardRequestBuilder;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateCardWithoutMobileNumber {

    @Autowired
    private CardScenarioContext context;

    @Given("a valid card creation request without mobile number")
    public void valid_card_creation_request_without_mobile_number() {

        CreateCardRequest req = CreateCardRequestBuilder.valid();
        req.setMobileNumber(null);

        context.setCreateCardRequest(req);
    }
}
