package ir.mahdi.sample.microservice.cards.bdd.steps;

import io.cucumber.java.en.*;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import ir.mahdi.sample.microservice.cards.dto.response.CardResponse;
import ir.mahdi.sample.microservice.cards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// Clean BDD + Clean Architecture
public class CardStepDefinitions {

    @Autowired
    private CardService cardService;

    private String mobileNumber;
    private CreateCardRequest request;
    private CardResponse response;

    @Given("a customer with mobile number {string}")
    public void customer_with_mobile(String mobile) {
        this.mobileNumber = mobile;
    }

    @Given("a valid card creation request")
    public void valid_request() {
        request = new CreateCardRequest();
        request.setMobileNumber(mobileNumber);
        request.setCardHolderName("Mehdi");
        request.setCardNumber("12054");
        request.setCvv("453");
        request.setExpiryDate("202605");
        request.setCardType("DEBIT");
    }

    @When("I request to create a card")
    public void create_card() {
        response = cardService.createCard(request);
    }

    @Then("the card should be created successfully")
    public void assert_created() {
        assertNotNull(response);
    }

    @Then("response should contain cardId")
    public void assert_card_id() {
        assertNotNull(response.getCardId());
        assertTrue(response.getCardId() > 0);
    }
}
