package pageobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import genericlibrary.BrowserUtilities;


public class LoginPage extends BrowserUtilities {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;

	}


	@FindBy(xpath = "//input[@id='email']")
	@CacheLookup
	public WebElement usernameField;

	@FindBy(xpath = "//input[@id='passwd']")
	@CacheLookup
	public WebElement passwordField;

	@FindBy(xpath = "//button[@id='SubmitLogin']//span")
	@CacheLookup
	public WebElement loginButton;


	public void loginUser() throws IOException {
	
		usernameField.sendKeys(prop.getProperty("userName"));
		passwordField.sendKeys(prop.getProperty("passWord"));
		loginButton.click();

	}

}
