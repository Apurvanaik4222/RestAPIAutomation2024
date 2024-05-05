package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\Users\\91762\\IdeaProjects\\RestAPIAutomation_2024\\src\\test\\java\\features\\AddPlace.feature"
        ,glue = "stepDefination",
        plugin = "json:target/jsonReports/cucumber-jsonReport.json",
        tags = " @DeletePlace")


public class Runner {
}
