package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Jaspal
 *
 */
public class HomePage {
	
	//**************ELEMENT LOCATORS**************
	private By emailTextbox = By.id("login-email");
	private By passwordTextbox = By.id("login-password");
	private By signInButton = By.id("login-submit");
	
	private WebDriver driver;
	
	
	//**************CONSTRUCTOR**************
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//**************PAGE METHODS**************
	public void open(String baseUrl) {
		driver.get(baseUrl);
	}
	
	public void login(String email, String password) {
		driver.findElement(emailTextbox).sendKeys(email);
    	driver.findElement(passwordTextbox).sendKeys(password);
    	driver.findElement(signInButton).click();
	}

}
