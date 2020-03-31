package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= "src/test/resources",
		glue= {"stepdefinitions"},
		tags= {"@smoketest"},
		plugin= {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/reports.html"},
		
		monochrome= true,
		strict= true
		
		)
public class Finalrunner {

}
