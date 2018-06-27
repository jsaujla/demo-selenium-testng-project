package selenium_testng_demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Jaspal
 *
 */
public class TestNgSeleniumAddedBrowserSelection {
	
	private WebDriver driver;
	
	@BeforeMethod
	public void beforeTest() {
    	launchBrowser("chrome");
    	driver.get("https://www.linkedin.com/");
	}
	
	@AfterMethod
	public void afterTest() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	@Test
	public void verifyLoginWithValidCredentials() {
    	driver.findElement(By.id("login-email")).sendKeys("add valid email"); // add valid email
    	driver.findElement(By.id("login-password")).sendKeys("add valid password"); // add valid password
    	driver.findElement(By.id("login-submit")).click();
    	String actualUserIdentityWelcomeText = driver.findElement(By.xpath("//a[@data-control-name='identity_welcome_message']")).getText().trim();
    	String expectedUserIdentityWelcomeText = "Welcome, Jaspal!"; // Change "Welcome, Jaspal!" to actual username (Welcome, Username!)
    	Assert.assertEquals(actualUserIdentityWelcomeText, expectedUserIdentityWelcomeText);
	}
	
	@Test
	public void verifyLoginWithInvalidCredentials() {
    	driver.findElement(By.id("login-email")).sendKeys("add valid email"); // add valid email
    	driver.findElement(By.id("login-password")).sendKeys("add invalid password"); // add invalid password
    	driver.findElement(By.id("login-submit")).click();
    	driver.findElement(By.xpath("//span[@id='session_password-login-error'][text()='The password you provided must have at least 6 characters.']")).isDisplayed();
    	String actualUrl = driver.getCurrentUrl();
    	String expectedUrl = "https://www.linkedin.com/uas/login-submit";
    	Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	
	private void launchBrowser(String browserName) {
		if(browserName.toLowerCase().contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/browser-drivers/geckodriver.exe");	
        	driver = new FirefoxDriver();
		}
		else if(browserName.toLowerCase().contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browser-drivers/chromedriver.exe");
        	driver = new ChromeDriver();
		}
		else if(browserName.toLowerCase().contains("ie") || browserName.toLowerCase().contains("internet explorer")) {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	        capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
	        capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        	System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/browser-drivers/IEDriverServer.exe");
        	driver = new InternetExplorerDriver(capabilities);
		}
		else if(browserName.toLowerCase().contains("edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/browser-drivers/MicrosoftWebDriver.exe");
        	driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

}
