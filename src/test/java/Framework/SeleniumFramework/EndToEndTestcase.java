package Framework.SeleniumFramework;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.SeleniumFrameworkPageObjectModel.AbstractClass;
import Framework.SeleniumFrameworkPageObjectModel.CartPage;
import Framework.SeleniumFrameworkPageObjectModel.LoginPage;
import Framework.SeleniumFrameworkPageObjectModel.OrderDetails;
import Framework.SeleniumFrameworkPageObjectModel.OrderPage;
import Framework.SeleniumFrameworkPageObjectModel.ProductPage;
import data.DataReader;

public class EndToEndTestcase extends BaseTest{
	
    //Here 2 product add in cart but only one product went for checkout
	@Test(dataProvider= "getData", groups= {"Purchase"})
	public void endToEndTest(HashMap<String, String> input) throws InterruptedException, IOException {
		
		//login
		loginPage.loginApplication(input.get("email"), input.get("password"));
		
		//add product to cart
		ProductPage products = new ProductPage(driver);
		List<WebElement> productList = products.getProductList();
		products.addToCart(input.get("productName"));
		
		CartPage carts= new CartPage(driver);
		carts.waitBeforeVisitingCartPage();
		carts.goToCartPage();
		
		WebElement product =carts.getProductInCart(input.get("productName"));
		System.out.println(product);
		//check list of item
		Boolean match = carts.verifyCartProduct(input.get("productName"));
		Assert.assertTrue(match);
		
		carts.clickCheckout();
		//carts.clickBuyNow(productName);
		
		//enter details
		OrderDetails details= new OrderDetails(driver);
		details.updateOrderDetails("India");
		
		//click on place order button
		details.submitOrder();

		Boolean verify =  details.verifyOrder("Thankyou for the order.");
		Assert.assertTrue(verify);
	}
	
	@Test(dependsOnMethods= {"endToEndTest"})
	public void viewOrderHistory() {
		loginPage.loginApplication("abcdkhan@gmail.com", "Abcd@#1234");
	    loginPage.goToOrdersPage();
	    OrderPage verifyOrders = new OrderPage(driver);
	    verifyOrders.verifyOrderDisplay(productName);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		DataReader data = new DataReader();
		List<HashMap<String, String>> listOfData = data.getJsonToMap(System.getProperty("user.dir")+ "\\src\\main\\java\\data\\data.json");
		
		return new Object[][] {{listOfData.get(0)},{listOfData.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"abcdkhan@gmail.com", "Abcd@#1234","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//	}
	
//	HashMap<String,String> data1 = new HashMap<String,String>();
//	data1.put("email", "abcdkhan@gmail.com");
//	data1.put("password", "Abcd@#1234");
//	data1.put("productName", "ZARA COAT 3");
//	
//	HashMap<String,String> data2 = new HashMap<String,String>();
//	data2.put("email", "shetty@gmail.com");
//	data2.put("password", "Iamking@000");
//	data2.put("productName", "ADIDAS ORIGINAL");
}

