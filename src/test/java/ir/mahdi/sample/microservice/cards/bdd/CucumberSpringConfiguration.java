package ir.mahdi.sample.microservice.cards.bdd;

import io.cucumber.spring.CucumberContextConfiguration;
import ir.mahdi.sample.microservice.cards.CardsApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = CardsApplication.class)
public class CucumberSpringConfiguration {
}
