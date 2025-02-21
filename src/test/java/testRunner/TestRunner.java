package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
//@CucumberOptions(features={".//features"}) // runs all feature files
@CucumberOptions(features={".//src/features/login.feature"}, glue = "stepDefinitions")
public class TestRunner {
}