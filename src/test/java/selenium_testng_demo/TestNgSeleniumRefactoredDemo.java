package selenium_testng_demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Jaspal
 *
 */
public class TestNgSeleniumRefactoredDemo {
	
	private WebDriver driver;
	
	@BeforeMethod
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/browser-drivers/geckodriver.exe");	
		driver = new FirefoxDriver();
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	driver.get("https://www.linkedin.com/");
	}
	
	@AfterMethod
	public void afterTest() {
		driver.quit();
	}
	
	@Test
	public void verifyLoginWithValidCredentials() {
    	driver.findElement(By.id("login-email")).sendKeys("add valid email"); // add valid email
    	driver.findElement(By.id("login-password")).sendKeys("add valid password"); // add valid password
    	driver.findElement(By.id("login-submit")).click();
    	String actualUserIdentityWelcomeText = driver.findElement(By.xpath("//a[@data-control-name='identity_welcome_message']")).getText();
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
	
}
