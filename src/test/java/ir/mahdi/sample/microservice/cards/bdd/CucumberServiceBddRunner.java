package ir.mahdi.sample.microservice.cards.bdd;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/service")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ir.mahdi.sample.microservice.cards.bdd.steps.common," +
        "ir.mahdi.sample.microservice.cards.bdd.steps.service," +
        "ir.mahdi.sample.microservice.cards.bdd.config.service," +
        "ir.mahdi.sample.microservice.cards.bdd.hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
public class CucumberServiceBddRunner {
}
