package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features/LoginPage.feature",
		glue="stepDefinitions",
		monochrome = true,
		plugin = {"pretty","html:target/cucumber-reports","json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
               },
		 publish = true 
		)
public class LoginPageRunner extends AbstractTestNGCucumberTests {

}
