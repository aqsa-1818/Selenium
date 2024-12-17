package Framework.SeleniumFrameworkPageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderDetails  extends AbstractClass  {

WebDriver driver;
	
	public OrderDetails(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	} 
	
	@FindBy(xpath= "//input[@placeholder='Select Country']")
	WebElement orderDetails;
	
	@FindBy(css= "button[type='button']:nth-of-type(2)")
	WebElement orderButton;
	
	@FindBy(css= ".hero-primary")
	WebElement verifyOrder;
	
	@FindBy(css= ".action__submit")
	WebElement placeOrder;
	
	public void updateOrderDetails(String countryName) {
		orderDetails.sendKeys(countryName);
		waitForElementApper(By.cssSelector(".ta-results"));
		orderButton.click();
	}
	
	public void submitOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", placeOrder);		
	}
	
	public Boolean verifyOrder(String message) {
		Boolean verify = verifyOrder.getText().equalsIgnoreCase(message);
		return verify;
		
		
	}
}