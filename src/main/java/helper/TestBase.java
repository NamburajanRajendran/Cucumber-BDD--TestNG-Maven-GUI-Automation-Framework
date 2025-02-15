package helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
	
	public WebDriver driver;
	private static Logger logger = LogManager.getLogger(TestBase.class);
	public static String getProperTy(String path,String name) throws IOException
	{
		Properties p1 = new Properties();		
		String propertyValue = null;
		try {
			FileInputStream f1 = new FileInputStream(path);
			p1.load(f1);
			propertyValue=p1.getProperty(name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
		
		return propertyValue;
	}
	
	
	
	public   WebDriver launchApplication() throws IOException
	{
		BasicConfigurator.configure();
		
		String pathGlobalProperty=System.getProperty("user.dir")+"\\src\\main\\resources\\global.Properties";
		String BrowserType=getProperTy(pathGlobalProperty,"BrowserType");
		String URL=getProperTy(pathGlobalProperty,"URL");
		logger.info("driver will launch in "+BrowserType+" Browser");
		if(BrowserType.equalsIgnoreCase("CHROME"))
		{
			driver = new ChromeDriver();
		}
		else if(BrowserType.equalsIgnoreCase("FireFox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			driver = new EdgeDriver();
		}		
		driver.get(URL);
		driver.manage().window().maximize();
		logger.info("Browser Launched with a title: "+driver.getTitle());
		return driver;		
	}
	
	public void explicitwait(int time,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
	    wait.until(d -> element.isDisplayed());
	}
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		TestBase t = new TestBase();
		t.launchApplication();
	}

}
