package ir.mahdi.sample.microservice.cards.bdd.steps.api;

import io.cucumber.java.en.Given;
import ir.mahdi.sample.microservice.cards.bdd.builder.CardBuilder;
import ir.mahdi.sample.microservice.cards.bdd.builder.CreateCardRequestBuilder;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import ir.mahdi.sample.microservice.cards.entity.Card;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateDuplicateCardAPISteps {

    @Autowired
    private CardScenarioContext context;

    @Autowired
    private CardRepository cardRepository;

    private String cardNumber;

    @Given("a card with card number {string} already exists")
    public void card_with_card_number_already_exists(String cardNumber) {
        this.cardNumber = cardNumber;
        Card card = CardBuilder.DefaultCard();
        card.setCardNumber(cardNumber);

        cardRepository.save(card);
    }

    @Given("a valid card creation request with existence card number")
    public void valid_card_creation_request_with_existence_card_number() {

        CreateCardRequest req = CreateCardRequestBuilder.valid();
        req.setCardNumber(this.cardNumber);

        context.setCreateCardRequest(req);
    }

}
