package Framework.SeleniumFrameworkPageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage  extends AbstractClass  {

WebDriver driver;
	
	public CartPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="ul[class='cartWrap ng-star-inserted']")
	List<WebElement> cartProduct;
	
	@FindBy(xpath= "//button[@routerlink='/dashboard/cart']")
	WebElement cartPage;
	
	@FindBy(css= ".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy(css= ".totalRow button")
	WebElement checkOut;
	
	public void waitBeforeVisitingCartPage() {
		waitForElementApper(By.cssSelector("#toast-container"));
		 waitForElementDisapper(By.cssSelector(".ng-animating"));
		
	}
	
	public void goToCartPage() {
		cartPage.click();
	}
	
	public WebElement getProductInCart(String productName) {		
		WebElement prod= cartProduct.stream().filter(product ->
		product.findElement(By.cssSelector(".cartSection h3")).getText().equals(productName)).findFirst().orElse(null);
		return prod; 
	}
	
	public Boolean verifyCartProduct(String productName) {	
		Boolean match = cartItems.stream().anyMatch(name -> name.getText().equals(productName));
		return match;		
	}
	
	public void clickCheckout() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkOut);		
	}
	
	public void clickBuyNow(String productName) {
		WebElement prod=getProductInCart(productName);
		prod.findElement(By.cssSelector(".btn.btn-primary")).click();
	}
}
