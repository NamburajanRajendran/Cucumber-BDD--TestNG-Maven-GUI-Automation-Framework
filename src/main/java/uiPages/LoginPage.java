package uiPages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LoginPage {
	
private WebDriver driver;	
@FindBy(how=How.ID,using="user-name")
private static WebElement userEmail;
@FindBy(how=How.CSS,using="#password")
private static WebElement userPwd;
@FindBy(how=How.ID_OR_NAME,using="login-button")
private static WebElement loginbtn;
@FindBy(how=How.XPATH,using="//button[@id='react-burger-menu-btn']")
private static WebElement logoutMenu;
@FindBy(how=How.XPATH,using="//a[@id='logout_sidebar_link']")
private static WebElement logoutbtn;
@FindBy(how=How.XPATH,using="//h3[@data-test='error']")
private static WebElement loginpageerror;

WebDriverWait wait;
public LoginPage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
	wait = new WebDriverWait(driver,Duration.ofSeconds(15));
}

public void enterUserEmail(String emailid)
{
	
	userEmail.sendKeys(emailid);
}

public void enterUserPassword(String password)
{
	
	userPwd.sendKeys(password);
}

public void clickLoginbtn()
{
	loginbtn.click();
}

public boolean checklogout()
{
	
	wait.until(d -> logoutMenu.isDisplayed());
	logoutMenu.click();
	
	return logoutbtn.isEnabled();
}

public void clickLoginoutbtn() throws InterruptedException
{
	wait.until(d -> logoutMenu.isEnabled());
	logoutMenu.sendKeys(Keys.ENTER);
	Thread.sleep(5000);
	wait.until(d -> logoutbtn.isDisplayed());
	WebElement myElement = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(logoutbtn));
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
	Actions act = new Actions(driver);
	act.moveToElement(logoutbtn).build().perform();
	
	
	Thread.sleep(10000);
}

public boolean checkloginPage()
{
	
	return loginbtn.isEnabled();
}

public String loginPageerrortext()
{
	wait.until(d -> loginpageerror.isDisplayed());
	return loginpageerror.getText();
}


}
