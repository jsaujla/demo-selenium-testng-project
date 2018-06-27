package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Jaspal
 *
 */
public class UserDashboardPage {
	
	//**************ELEMENT LOCATORS**************
	private By userIdentityWelcomeText = By.xpath("//a[@data-control-name='identity_welcome_message']");
	
	private WebDriver driver;
	
	
	//**************CONSTRUCTOR**************
	public UserDashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//**************PAGE METHODS**************
	public String getUserIdentityWelcomeText() {
		return driver.findElement(userIdentityWelcomeText).getText().trim();
	}

}
