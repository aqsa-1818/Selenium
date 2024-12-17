package Framework.SeleniumFrameworkPageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends AbstractClass  {

WebDriver driver;
	
	public OrderPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	} 
	
	@FindBy(css= "tr td:nth-child(3)")
	List<WebElement> orderDetails;
	
	public Boolean verifyOrderDisplay(String productName) {
			Boolean orders= orderDetails.stream().anyMatch(name -> name.getText().equals(productName));
			return orders;
		}

}