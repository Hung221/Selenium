package webDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
public class Iframe_WindowsTab {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
				
	}
	@Test 
	public void TestCase1() {
		driver.get("https://kyna.vn/");
		WebElement FbIframe = driver.findElement(By.xpath("//iframe[contains(@src,'facebook.com')]"));
		driver.switchTo().frame(FbIframe);
		// Veerify Likes
		WebElement Like = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div"));
		AssertJUnit.assertTrue(Like.isDisplayed());
		driver.switchTo().defaultContent();
		WebElement ChatBox = driver.findElement(By.xpath("//div[contains(@class,'chatButton')]//div[@class='button_text']"));
		ChatBox.click();
		SleepInSecond(2);
		driver.findElement(By.xpath("//div[@class='editing field profile_field']//input[@placeholder='Nhập tên của bạn']")).sendKeys("AutomationTest");
		
	}
	public void SleepInSecond(long Seconds) {
		try {
			Thread.sleep(Seconds * 1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public void AfterClass() {
		driver.quit();
		
	}
}
