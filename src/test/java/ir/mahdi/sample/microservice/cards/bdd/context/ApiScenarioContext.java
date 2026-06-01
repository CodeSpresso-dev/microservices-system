package ir.mahdi.sample.microservice.cards.bdd.context;

import lombok.Getter;
import lombok.Setter;
import org.springframework.test.web.servlet.MvcResult;

@Getter
@Setter
public class ApiScenarioContext {
    private MvcResult mvcResult;
    private String responseBody;
}
