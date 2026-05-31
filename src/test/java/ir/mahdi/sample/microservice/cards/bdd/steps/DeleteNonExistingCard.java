package ir.mahdi.sample.microservice.cards.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import ir.mahdi.sample.microservice.cards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteNonExistingCard {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardScenarioContext context;

    @When("I delete the card with id {long}")
    public void delete_card_with_id(Long id) {

        try {
            cardService.deleteCard(id);
        } catch (Exception ex) {
            context.setException(ex);
        }
    }
}
