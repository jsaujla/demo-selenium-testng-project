package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Jaspal
 *
 */
public class LoginSubmitPage {
	
	private WebDriver driver;
	
	
	//**************CONSTRUCTOR**************
	public LoginSubmitPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//**************PAGE METHODS**************
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public void isErrorMessageDisplayed(String errorMessage) {
		driver.findElement(By.xpath("//span[@id='session_password-login-error'][text()='"+errorMessage+"']")).isDisplayed();
	}

}
