package Framework.SeleniumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.*;

import Framework.SeleniumFrameworkPageObjectModel.LoginPage;

public class BaseTest {
	public String productName="ADIDAS ORIGINAL";
	public WebDriver driver;
	public LoginPage loginPage;
	public ExtentReports extent ;
	public WebDriver driver1;
	
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(file);
		
		//ternary operators to change browser at run time
		String browserName= System.getProperty("browser")!=null? System.getProperty("browser"):prop.getProperty("browser");
		
		if(browserName.contains("chrome")){
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				//System.out.println("Running in headlessmode");
			options.addArguments("headless");
			}
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900)); //full screen
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
		}
		else if (browserName.equalsIgnoreCase("edge")){
			
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;	
		File souce= ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(souce, new File(System.getProperty("user.dir")+ "//reports" + testCaseName + ".png") );
		return System.getProperty("user.dir")+ "//reports" + testCaseName + ".png";
	}
	
	 public String getScreenShot1(String testCaseName, WebDriver driver) throws IOException {
	        String screenshotPath = null;
	        try {
	            //take screenshot and save it in a file
	            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            //copy the file to the required path
	            File destinationFile = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
	            FileHandler.copy(sourceFile, destinationFile);
	            String[] relativePath = destinationFile.toString().split("reports");
	            screenshotPath = ".\\" + relativePath[1];
	        } catch (Exception e) {
	            System.out.println("Failure to take screenshot " + e);
	        }
	        return screenshotPath;
	    }

	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException {
		driver= initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goToPage("https://rahulshettyacademy.com/client/");
		return loginPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeApplication() {
		driver.close();
	}
	
//	@BeforeTest
//	public void extentReports() {
//		String path = System.getProperty("user.dir")+"\\reports\\index.html";
//		ExtentSparkReporter report = new ExtentSparkReporter(path);
//		report.config().setDocumentTitle("Demo Test");
//				
//	    extent = new ExtentReports();
//		extent.attachReporter(report);
//		extent.setSystemInfo("Tester", "Abcd");
//	}
//	
//	@AfterTest
//	public void collectReports() {
//		extent.flush();
//	}
}
