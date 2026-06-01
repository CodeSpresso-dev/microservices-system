package ir.mahdi.sample.microservice.cards.bdd.context;

import ir.mahdi.sample.microservice.cards.dto.response.CardResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceScenarioContext {
    private CardResponse response;
    private Exception exception;
}
