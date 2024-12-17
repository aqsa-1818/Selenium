package Framework.SeleniumFrameworkPageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AbstractClass {

WebDriver driver;
	
	public ProductPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css= ".mb-3")
	List<WebElement> products;
	
	public List<WebElement> getProductList() {
		waitForElementApper(By.cssSelector(".mb-3"));
		return products;
	}
	
	public WebElement getProductName(String productName) {
		WebElement prod= getProductList().stream().filter(product ->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}

	public void addToCart(String productName) {
		WebElement prod= getProductName(productName);  
		prod.findElement(By.cssSelector(".w-10 ")).click();
	}
}
