package webDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Exc1 {

	WebDriver driver;
	
	@BeforeClass
	public void beforeCla() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://live.demoguru99.com/");
	}	
	@Test
	public void TestCase1(){
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
// sai	driver.findElement(By.xpath("//li[@class='first'][1]/a[@title='My Account']")).click();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.id("email")).sendKeys("");
	driver.findElement(By.name("login[password]")).sendKeys("");
	driver.findElement(By.name("send")).click();
	//driver.findElement(By.id("advice-required-entry-email")).getText();
	AssertJUnit.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
	
	}	
	@AfterClass
	public void After() {
		driver.quit();
	
		
	}
}
