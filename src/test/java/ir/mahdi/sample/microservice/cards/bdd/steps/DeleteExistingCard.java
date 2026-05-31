package ir.mahdi.sample.microservice.cards.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.entity.Card;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import ir.mahdi.sample.microservice.cards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class DeleteExistingCard {

    @Autowired
    private CardScenarioContext context;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    @Given("a card exists")
    public void a_card_exists() {

        Card card = Card.builder()
                .mobileNumber("09123456789")
                .cardHolderName("Mehdi")
                .cardNumber("55555")
                .cvv("123")
                .expiryDate("202605")
                .cardType("DEBIT")
                .status("ACTIVE")
                .build();

        Card saved = cardRepository.save(card);

        context.setCardId(saved.getId());
    }

    @When("I delete the card")
    public void delete_card() {

        try {
            cardService.deleteCard(context.getCardId());
        } catch (Exception ex) {
            context.setException(ex);
        }
    }

    @Then("the card should be removed from database")
    public void verify_deleted_card() {

        assertFalse(
                cardRepository.findById(context.getCardId()).isPresent()
        );
    }
}
