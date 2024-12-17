package Framework.SeleniumFrameworkPageObjectModel;



//public class ExtentReport {
//	ExtentReports extent ;
//
//	@BeforeTest()
//	public void extentConfig() {
//		String path = System.getProperty("user.dir")+"\\reports\\index.html";
//		ExtentSparkReporter report = new ExtentSparkReporter(path);
//		report.config().setDocumentTitle("Demo Test");
//				
//	    extent = new ExtentReports();
//		extent.attachReporter(report);
//		extent.setSystemInfo("Tester", "Abcd");
//		
//	}
//	@Test
//	public void extentReportDemo() {
//		extent.createTest("Demo");
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://www.google.com/");
//		System.out.println(driver.getTitle());
//		driver.close();
//		extent.flush();
//	}
//}
