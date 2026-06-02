package ir.mahdi.sample.microservice.cards.bdd.config.container;

import io.cucumber.spring.CucumberContextConfiguration;
import ir.mahdi.sample.microservice.cards.integration.AbstractIntegrationTest;
import jakarta.annotation.PostConstruct;

@CucumberContextConfiguration
public class CucumberSpringConfiguration extends AbstractIntegrationTest {

    @PostConstruct
    void init() {
        System.out.println(">>> CUCUMBER SPRING CONFIG LOADED");
    }
}
