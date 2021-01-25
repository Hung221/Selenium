package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class PopUpIframe {
	WebDriver driver;
	JavascriptExecutor JsExecutor;
	Actions action;
	
	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		JsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	
	public void TestCasePopUp1() {
		driver.get("https://www.zingpoll.com/");
		driver.findElement(By.id("Loginform")).click();
		SleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
		driver.findElement(By.xpath("//h4[text()='Sign In']/preceding-sibling::button")).click();
		SleepInSecond(2);
		Assert.assertFalse(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
	}
	@Test
	public void RandDomPopUp_InDom() {
		String KeyW = "Selenium";
		driver.get("https://blog.testproject.io/");
		WebElement PopUp = driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']"));
		SleepInSecond(3);
		WebElement LoseBtn = driver.findElement(By.xpath("//div[@id='close-mailch']"));
		if (PopUp.isDisplayed()) {
			LoseBtn.click();
		}
		else {
			driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys(KeyW);
			driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']/following-sibling::span")).click();
		}
		//Get locator contains the Title of the Book suggested
		List <WebElement> AllItems = driver.findElements(By.xpath("//div[@class='posts-wrap']//h3[@class='post-title']/a"));
		//WebElement a = AllItems.get(1);
		//String aT = a.getText();
		int NumberItem = AllItems.size();
		for (int i = 1 ; i< NumberItem; i++) {
			Assert.assertTrue(AllItems.get(i).getText().contains(KeyW));
			System.out.println(AllItems.get(i).getText() + "contains" + " " + KeyW);
		}
		SleepInSecond(5);
	}
	public void SleepInSecond(long secondsSlepp) {
		try {
			Thread.sleep(secondsSlepp * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

}
