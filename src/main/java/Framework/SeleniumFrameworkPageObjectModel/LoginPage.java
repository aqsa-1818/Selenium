package Framework.SeleniumFrameworkPageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractClass {
	WebDriver driver;
	
	public LoginPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath= "//input[@type='email']")
	WebElement userEmail;

	@FindBy(xpath= "//input[@type='password']")
	WebElement userPass;
	
	@FindBy(className= "login-btn")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public void loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPass.sendKeys(password);
		submit.click();
	}
	
	public String getErrorMessage()
	{
		waitForWebElementDisapper(errorMessage);
		return errorMessage.getText();
		
	}
}
