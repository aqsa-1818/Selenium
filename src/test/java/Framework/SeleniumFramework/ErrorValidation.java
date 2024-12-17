package Framework.SeleniumFramework;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.SeleniumFrameworkPageObjectModel.CartPage;
import Framework.SeleniumFrameworkPageObjectModel.ProductPage;

public class ErrorValidation extends BaseTest{
	
	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidation() throws InterruptedException, IOException {
		
		//login
		loginPage.loginApplication("abdkhan@gmail.com", "Abcd@#1234");
		Assert.assertEquals("Incorrect email or password", loginPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws InterruptedException, IOException {
		
		//login
		loginPage.loginApplication("abcdkhan@gmail.com", "Abcd@#1234");
		ProductPage products = new ProductPage(driver);
		List<WebElement> productList = products.getProductList();
		products.addToCart(productName);
		
		CartPage carts= new CartPage(driver);
		carts.waitBeforeVisitingCartPage();
		carts.goToCartPage();
		
		WebElement product =carts.getProductInCart(productName);
		System.out.println(product);
		//check list of item
		Boolean match = carts.verifyCartProduct(productName);
		Assert.assertTrue(match);
	}
}

		
