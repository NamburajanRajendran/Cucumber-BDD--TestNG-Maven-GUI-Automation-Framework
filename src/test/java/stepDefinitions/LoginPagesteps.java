package stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import helper.TestBase;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;  
import org.apache.log4j.LogManager;  
import org.apache.log4j.Logger;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uiPages.LoginPage;

public class LoginPagesteps {
	
	public  WebDriver driver;
	private static Logger logger = LogManager.getLogger(LoginPagesteps.class);
	public LoginPage login ;
	TestBase utl ;
	
	@Before
	public void setUp() throws IOException
	{
		utl = new TestBase();
		driver = utl.launchApplication();
	}
	@Given("I am on the login page")
	public void i_am_on_the_login_page() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		login = new LoginPage(driver);		
		//Assert.assertEquals("Your store. Login", driver.getTitle());
		BasicConfigurator.configure();
	}

	@Given("I have entered a valid userName as {string} and Password as {string}")
	public void i_have_entered_a_valid_user_name_as_and_password_as(String useremail, String userpassword) {
	    // Write code here that turns the phrase above into concrete actions
		
		login.enterUserEmail(useremail);
		login.enterUserPassword(userpassword);		
	}

	@When("I click on login button")
	public void i_click_on_login_button() {
	    // Write code here that turns the phrase above into concrete actions
		login.clickLoginbtn();	
		
	}

	@Then("I should be logged in Succesfully")
	public void i_should_be_logged_in_succesfully() {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(login.checklogout(), true);
		logger.info("User Logged in Succesfully");
	}

	

	@Given("I have entered a  userName as {string} and Password as {string}")
	public void i_have_entered_a_user_name_as_and_password_as(String useremail, String userpassword) {
	    // Write code here that turns the phrase above into concrete actions
		login.enterUserEmail(useremail);
		login.enterUserPassword(userpassword);		
	}

	@Then("I should get an error message as {string}")
	public void i_should_get_an_error_message(String errormessage) {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(login.loginPageerrortext(),errormessage);
		logger.info("User not elegible to login");
	}
	
	@After(order=1)
	public void capturescreenshots(Scenario scenario)
	{
		if (scenario.isFailed()) {

			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] src = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(src, "image/png", "screenshot");
			}
	}
	
	@After(order=0)
	public void teardown()
	{
		if(driver!=null)
		{
			driver.quit();
		}
	}
	

}
