package ir.mahdi.sample.microservice.cards.bdd.config.api;

import io.cucumber.spring.CucumberContextConfiguration;
import ir.mahdi.sample.microservice.cards.CardsApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = CardsApplication.class)
public class CucumberSpringApiBddConfiguration {
}
