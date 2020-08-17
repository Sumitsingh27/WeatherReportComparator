package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true,features="src/test/java/features/webWeatherReport.feature",glue={"stepDefinations"})
// tags={"@RunAPIToGetData"}

public class TestRunner { 

}
