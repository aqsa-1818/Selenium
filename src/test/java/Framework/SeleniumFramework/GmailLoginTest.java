package Framework.SeleniumFramework;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class GmailLoginTest {

	@Test()
	public void gmailLoginTest() {
		
	WebDriver driver =new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
	driver.get("https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&emr=1&followup=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&ifkv=AeZLP99TM7VrZftVSJfRv5nN7hFDLyQgsuCJL3KrgGKxfHQxL6U3WqxuQYjbtVrH328ezLj_8sD7jw&osid=1&passive=1209600&service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S-1122011031%3A1734187892665432&ddm=1");
	driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Abcd@gmail.com");
	driver.findElement(By.cssSelector("span[class='VfPpkd-vQzf8d']")).click();
	}
}
