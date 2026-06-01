package ir.mahdi.sample.microservice.cards.bdd.context;

import io.cucumber.spring.ScenarioScope;
import ir.mahdi.sample.microservice.cards.dto.request.CreateCardRequest;
import ir.mahdi.sample.microservice.cards.dto.response.CardResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;

@Component
@ScenarioScope
public class CardScenarioContext {

    private final ApiScenarioContext api = new ApiScenarioContext();
    private final ServiceScenarioContext service = new ServiceScenarioContext();
    private final SharedScenarioContext shared = new SharedScenarioContext();

    public void setMvcResult(MvcResult mvcResult) {
        this.api.setMvcResult(mvcResult);
    }

    public void setResponseBody(String responseBody) {
        this.api.setResponseBody(responseBody);
    }

    public MvcResult getMvcResult() {
        return this.api.getMvcResult();
    }

    public String getResponseBody() {
        return this.api.getResponseBody();
    }

    public void setCardResponse(CardResponse response) {
        this.service.setResponse(response);
    }

    public void setException(Exception exception) {
        this.service.setException(exception);
    }

    public CardResponse getCardResponse() {
        return this.service.getResponse();
    }

    public Exception getException() {
        return this.service.getException();
    }

    public void setCreateCardRequest(CreateCardRequest request) {
        this.shared.setRequest(request);
    }

    public void setCardId(Long cardId) {
        this.shared.setCardId(cardId);
    }

    public void setMobileNumber(String mobileNumber) {
        this.shared.setMobileNumber(mobileNumber);
    }

    public CreateCardRequest getCreateCardRequest() {
        return this.shared.getRequest();
    }

    public Long getCardId() {
        return this.shared.getCardId();
    }

    public String getMobileNumber() {
        return this.shared.getMobileNumber();
    }
}
