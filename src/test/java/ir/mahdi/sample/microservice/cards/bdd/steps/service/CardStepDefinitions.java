package ir.mahdi.sample.microservice.cards.bdd.steps.service;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
// Clean BDD + Clean Architecture
// BDD Integration Testing
public class CardStepDefinitions {

    @Autowired
    private CardScenarioContext context;

    @Given("a valid card creation request with mobile number {string}")
    public void valid_card_creation_request_with_mobile_number(String mobile) {
        CreateCardRequest request = new CreateCardRequest();
        request.setMobileNumber(mobile);
        request.setCardHolderName("Mehdi");
        request.setCardNumber("12054");
        request.setCvv("453");
        request.setExpiryDate("202605");
        request.setCardType("DEBIT");
        context.setRequest(request);
    }


    @Then("the card should be created successfully")
    public void assert_created() {
        assertNotNull(context.getResponse());
    }

    @Then("response should contain cardId")
    public void assert_card_id() {
        assertNotNull(context.getResponse().getCardId());
        assertTrue(context.getResponse().getCardId() > 0);
    }
}
