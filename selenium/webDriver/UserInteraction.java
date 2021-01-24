package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class UserInteraction {
// release dung cho click and hold
// Khi chay case related to UserInteraction ko dung den chuot & ban phim
// # giua driver.click and action.click action click co hanh dong move truoc moi click
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor JsExecutor;
	Actions action;
	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		JsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	public void HoverTest1() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		WebElement Hover1 = driver.findElement(By.id("age"));
		SleepInSecond(3);
		action.moveToElement(Hover1).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector(".ui-tooltip-content")).isDisplayed());
		
	}
	
	public void HoverTest2() {
		driver.get("https://www.myntra.com/");
		WebElement Hover2 =driver.findElement(By.xpath("//a[@data-group='kids']"));
		action.moveToElement(Hover2).perform();
		SleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		String a = driver.getTitle();
		System.out.println(a);
		Assert.assertEquals("Kids Home Bath - Buy Kids Home Bath online in India", a);
	}
	@Test 
	public void HoverTest3() {
		driver.get("https://hn.telio.vn/");
		//WebElement Hover3 = 
		driver.findElement(By.xpath("(//a[@class='menu-link'])[3]")).click();;
		//action.moveToElement(Hover3).perform();
		SleepInSecond(3);
		//driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();

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
