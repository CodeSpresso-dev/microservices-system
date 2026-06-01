package ir.mahdi.sample.microservice.cards.bdd.steps.service;

import io.cucumber.java.en.Given;
import ir.mahdi.sample.microservice.cards.bdd.builder.CreateCardRequestBuilder;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import ir.mahdi.sample.microservice.cards.entity.Card;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PreventDuplicateCardCreationSteps {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardScenarioContext context;

    @Given("a card with number {string} already exists")
    public void existing_card(String cardNumber) {

        Card existingCard = Card.builder()
                .mobileNumber("09123456789")
                .cardHolderName("Existing User")
                .cardNumber(cardNumber)
                .cvv("123")
                .expiryDate("202605")
                .cardType("DEBIT")
                .status("ACTIVE")
                .build();

        cardRepository.save(existingCard);
    }


    @Given("a valid card creation request with card number {string}")
    public void request(String cardNumber) {

        CreateCardRequest req = CreateCardRequestBuilder.valid();
        req.setCardNumber(cardNumber);

        context.setCreateCardRequest(req);
    }
}
