package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Drop_down_Angular {
	
	WebDriver driver; 
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");		
	}
	@Test
	public void SelectOptionInAngular() {
		HandleAngular("(//span[@class='e-input-group e-control-wrapper e-ddl e-lib e-keyboard'])[1]","//div[@id='games_popup']//li","Badminton");
		Assert.assertEquals(getTextAngular(), "Badminton");
	}
	public void HandleAngular(String ParentItem, String AllItem, String ExpectedItem) {
		driver.findElement(By.xpath(ParentItem)).click();
		List<WebElement> AllItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(AllItem)));
		for (WebElement Item:AllItems) {
			String ActualItem = Item.getText();
			if (ActualItem.equals(ExpectedItem)){
				Item.click();
				break;
			}			
			}
		}
	public String getTextAngular() {
		return (String) jsExecutor.executeScript("return $('select[name='game'] option\').text");
	}
	
	@AfterClass
	public void AfterClass() {
	driver.quit();

}
	}
