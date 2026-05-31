package ir.mahdi.sample.microservice.cards.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import ir.mahdi.sample.microservice.cards.bdd.builder.CreateCardRequestBuilder;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import ir.mahdi.sample.microservice.cards.entity.Card;
import ir.mahdi.sample.microservice.cards.exception.CardAlreadyExistsException;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CardDuplicateSteps {

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

        context.setRequest(req);
    }

    @Then("CardAlreadyExistsException should be thrown")
    public void thenError() {
        assertNotNull(context.getException());
        assertThat(context.getException())
                .isInstanceOf(CardAlreadyExistsException.class);
    }
}
