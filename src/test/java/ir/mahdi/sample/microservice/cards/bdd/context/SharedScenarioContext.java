package ir.mahdi.sample.microservice.cards.bdd.context;

import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SharedScenarioContext {
    private CreateCardRequest request;
    private Long cardId;
    private String mobileNumber;
}
