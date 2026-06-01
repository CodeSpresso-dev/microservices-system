package ir.mahdi.sample.microservice.cards.bdd.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ir.mahdi.sample.microservice.cards.bdd.context.CardScenarioContext;
import ir.mahdi.sample.microservice.cards.reository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Hooks {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    CardScenarioContext context;

    @Before
    public void beforeScenario() {
        System.out.println("Scenario Started");
        cardRepository.deleteAll();
    }

    @After
    public void afterScenario() {
        System.out.println("Scenario Finished");
        context.setMvcResult(null);

        context.setException(null);
        context.setCardResponse(null);
    }
}
