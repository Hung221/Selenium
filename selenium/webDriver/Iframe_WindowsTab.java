package webDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
public class Iframe_WindowsTab {
	WebDriver driver;
	WebDriverWait explicitWait;
	Select select;
	// 3 steps import >> call name >> create
	
	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
				
	}
	@Test 
	public void TestCase1_Frame_IFrame() {
		driver.get("https://kyna.vn/");
		WebElement FbIframe = driver.findElement(By.xpath("//iframe[contains(@src,'facebook.com')]"));
		driver.switchTo().frame(FbIframe);
		// Veerify Likes
		WebElement Like = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div"));
		//26k likes displayed
		AssertJUnit.assertTrue(Like.isDisplayed());
		// switch to default before 
		driver.switchTo().defaultContent();
		SleepInSecond(2);
		// switch to chat box Iframe
		driver.switchTo().frame("cs_chat_iframe");
		// click on chat button
		driver.findElement(By.xpath("//div[contains(@class,'chatButton')]//div[@class='button_text']//following-sibling::div[contains (@class, 'overlay')]")).click();
		SleepInSecond(2);
		// send infor
		driver.findElement(By.xpath("//div[@class='editing field profile_field']//input[@placeholder='Nhập tên của bạn']")).sendKeys("AutomationTest");
		driver.findElement(By.xpath("//div[@class='editing field profile_field']//input[@placeholder='Nhập số điện thoại của bạn']")).sendKeys("09090909");
		select = new Select(driver.findElement(By.id("serviceSelect")));
		// Verify only one option can be selected
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.name("message")).sendKeys("Test Auto");
		driver.findElement(By.xpath("//input[@value='Gửi tin nhắn']")).click();
		//String NameisSend = driver.findElement(By.xpath("//div[@class='floater_inner_seriously']//label[contains(@class, 'logged_in_name')]")).getText();
		//System.out.println(NameisSend);
		//String PhoneisSend = driver.findElement(By.xpath("//div[@class='floater_inner_seriously']//label[contains(@class, 'logged_in_phone')]")).getText();
		//Assert.assertTrue(NameisSend.contains("abc"));
		//Assert.assertTrue(PhoneisSend.contains("09090909"));
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
		driver.findElement(By.xpath("//button[@class='search-button']")).click();
		SleepInSecond(2);
		List<WebElement> ElementContains = driver.findElements(By.xpath("//div[@class='k-box-card-wrap clearfix']//div[@class='content']//h4"));
		for (WebElement EachElement:ElementContains) {
			String actualItemValue =EachElement.getText();
			Assert.assertTrue(actualItemValue.contains("Excel"));
		}
		SleepInSecond(3);
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
