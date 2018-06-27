package selenium_testng_demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

import pages.HomePage;
import pages.LoginSubmitPage;
import pages.UserDashboardPage;

public class TestNgSeleniumPageObject {
	
	//**************VARIABLE/OBJECT DECLARATION**************
	private WebDriver driver;
	private String baseUrl;
	private String browserName;
	private Properties propertyFile;
	private FileInputStream fileInputStream;
	
	private HomePage homePage;
	private UserDashboardPage userDashboardPage;
	private LoginSubmitPage loginSubmitPage;
	
	@BeforeMethod
	public void beforeTest() {
		//**************READ BROWSER, URL INFORMATION FROM PROPERTIES FILE**************
		propertyFile = loadPropertiesFile(propertyFile, System.getProperty("user.dir") + "/src/test/resources/propertyFile.properties");
		browserName = propertyFile.getProperty("browser");
		baseUrl = propertyFile.getProperty("baseUrl");		
		
    	launchBrowser(browserName);
    	
    	//**************PAGE INSTANTIATIONS**************
    	homePage = new HomePage(driver);
    	userDashboardPage = new UserDashboardPage(driver);
    	loginSubmitPage = new LoginSubmitPage(driver);
    	
    	homePage.open(baseUrl);
	}
	
	@AfterMethod
	public void afterTest() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	@Test
	public void verifyLoginWithValidCredentials() {
		homePage.login("add valid email", "add valid password"); // add valid email and password
    	String actualUserName = userDashboardPage.getUserIdentityWelcomeText();
    	String expectedUserName = "Welcome, Jaspal!"; // Change "Welcome, Jaspal!" to actual username (Welcome, Username!)
    	
    	//**************ASSERTION**************
    	Assert.assertEquals(actualUserName, expectedUserName);
	}
	
	@Test
	public void verifyLoginWithInvalidCredentials() {
		homePage.login("add valid email", "add invalid password"); // add valid email and invalid password
    	loginSubmitPage.isErrorMessageDisplayed("The password you provided must have at least 6 characters.");
    	String actualUrl = loginSubmitPage.getCurrentUrl();
    	String expectedUrl = "https://www.linkedin.com/uas/login-submit";
    	
    	//**************ASSERTION**************
    	Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	
	//**************PRIVATE METHOD TO LAUNCH BROWSER**************
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
	
	
	//**************PRIVATE METHOD TO READ PROPERTIES FILE**************
	private Properties loadPropertiesFile(Properties propertiesObject, String filePath) {
        try {
        	propertiesObject = new Properties();
            fileInputStream = new FileInputStream(filePath);
            propertiesObject.load(fileInputStream);
            return propertiesObject;
        }
        catch (IOException io) {
            io.printStackTrace();
            return null;
        }
        finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                }
                catch (IOException io) {
                    io.printStackTrace();
                }
            }
        }
    }
	
}
