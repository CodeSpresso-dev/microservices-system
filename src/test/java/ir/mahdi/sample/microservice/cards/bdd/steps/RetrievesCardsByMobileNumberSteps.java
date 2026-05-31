package ir.mahdi.sample.microservice.cards.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.mahdi.sample.microservice.cards.dto.response.CardResponse;
import ir.mahdi.sample.microservice.cards.entity.Card;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import ir.mahdi.sample.microservice.cards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RetrievesCardsByMobileNumberSteps {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    private List<CardResponse> cardResponses;

    @Given("2 cards exist for mobile number {string}")
    public void cards_exist_for_mobile(String mobileNumber) {

        Card card1 = Card.builder()
                .mobileNumber(mobileNumber)
                .cardHolderName("Mehdi")
                .cardNumber("11111")
                .cvv("123")
                .expiryDate("202605")
                .cardType("DEBIT")
                .status("ACTIVE")
                .build();

        Card card2 = Card.builder()
                .mobileNumber(mobileNumber)
                .cardHolderName("Mehdi")
                .cardNumber("22222")
                .cvv("456")
                .expiryDate("202605")
                .cardType("DEBIT")
                .status("ACTIVE")
                .build();

        cardRepository.save(card1);
        cardRepository.save(card2);
    }

    @When("I request to fetch cards for mobile number {string}")
    public void fetch_cards_for_mobile(String mobileNumber) {

        cardResponses = cardService.fetchCards(mobileNumber);
    }

    @Then("{int} cards should be returned")
    public void verify_card_count_for_mobile(int expectedCount) {

        assertNotNull(cardResponses);
        assertEquals(expectedCount, cardResponses.size());
    }
}
