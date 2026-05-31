package ir.mahdi.sample.microservice.cards.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.exception.ResourceNotFoundException;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import ir.mahdi.sample.microservice.cards.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DeleteNonExistingCard {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    @Autowired
    private CardScenarioContext context;

    @Given("card with id {long} does not exist")
    public void card_does_not_exist(Long id) {

        cardRepository.deleteAll();
    }

    @When("I delete the card with id {long}")
    public void delete_card_with_id(Long id) {

        try {
            cardService.deleteCard(id);
        } catch (Exception ex) {
            context.setException(ex);
        }
    }

    @Then("ResourceNotFoundException should be thrown")
    public void resource_not_found_exception_should_be_thrown_for_non_existing_card() {

        assertNotNull(context.getException());
        assertInstanceOf(
                ResourceNotFoundException.class,
                context.getException()
        );
    }

}
