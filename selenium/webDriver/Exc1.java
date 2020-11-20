package webDriver;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Exc1 {

	WebDriver driver;
	Random rand = new Random();
	String Em;
	
	
	@BeforeClass
	public void beforeCla() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		driver.manage().window().maximize();
		driver.get("https://live.demoguru99.com/");
		
	}
//	@Test
//	public void TestCase2(){
//		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
//		// sai	driver.findElement(By.xpath("//li[@class='first'][1]/a[@title='My Account']")).click();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.findElement(By.id("email")).sendKeys("123@123");
//		driver.findElement(By.name("login[password]")).sendKeys("123");
//		driver.findElement(By.name("send")).click();
//		driver.findElement(By.id("advice-validate-email-email")).getText();
//		AssertJUnit.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
//	}	
//	@Test
//	public void TestCase1(){
//		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
//		// sai	driver.findElement(By.xpath("//li[@class='first'][1]/a[@title='My Account']")).click();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.findElement(By.id("email")).sendKeys("");
//		driver.findElement(By.name("login[password]")).sendKeys("");
//		driver.findElement(By.name("send")).click();
//		//driver.findElement(By.id("advice-required-entry-email")).getText();
//		AssertJUnit.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
//	}
	
	@Test
	public void Testcase5(){
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//div[@class=\"col-1 new-users\"]//a")).click();
		driver.findElement(By.id("firstname")).sendKeys("abc");
		driver.findElement(By.id("lastname")).sendKeys("def");
		Em = "automation" + rand.nextInt(9999) + "@gmail.com";
		driver.findElement(By.id("email_address")).sendKeys(Em);
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		AssertJUnit.assertEquals(driver.findElement(By.xpath("//li[@class=\"success-msg\"]//span")).getText(), "Thank you for registering with Main Website Store.");
		String Text = driver.findElement(By.xpath("//h3[text()=\'Contact Information\']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(Text.contains("abc"));
		Assert.assertTrue(Text.contains("def"));
		Assert.assertTrue(Text.contains(Em));
		driver.findElement(By.xpath("//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		//a[href$=".pdf"]	Selects every <a> element whose href attribute value ends with ".pdf"
		driver.findElement(By.cssSelector("img[src$='logo.png']")).isDisplayed();
		//String tit =driver.getTitle();
		//System.out.println(tit);
		//Assert.assertTrue(tit.contains("Magento Commerce"));
		
		
	}
	@AfterClass
	public void After() {
		driver.quit();
		
	}
}


