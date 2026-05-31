package ir.mahdi.sample.microservice.cards.bdd.context;

import io.cucumber.spring.ScenarioScope;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import ir.mahdi.sample.microservice.cards.dto.response.CardResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
@Getter
@Setter
public class CardScenarioContext {

    private CreateCardRequest request;
    private CardResponse response;
    private Exception exception;
    private String mobileNumber;
}
