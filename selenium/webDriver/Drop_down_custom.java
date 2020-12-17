package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.org.apache.bcel.internal.generic.AALOAD;

import junit.framework.Assert;

public class Drop_down_custom {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		explicitWait = new WebDriverWait(driver, 30);
}
	@Test
	public void JqueryCustome() {
		HandleJque("//span[@id='number-button']/span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']", 
				"//ul[@id='number-menu']//div[@class='ui-menu-item-wrapper']", 
				"19");
		Assert.assertEquals("19",driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText());
		
	}
	public void HandleJque(String ParentItem, String AllItem,String ExpectedItem) {
		// select parent to show all selected options //span[@id='speed-button']//span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']
		driver.findElement(By.xpath(ParentItem)).click();
		// Wait to all elements is shown
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(AllItem)));
		// Luu lai thanh List va duyet qua cac Item, AllItem la xapth dai dien cho all options
		List<WebElement> ListAllElements= driver.findElements(By.xpath(AllItem)); 
		int ListLen = ListAllElements.size();
		for (int i =0; i < ListLen; i++) {
			String Actualtem = ListAllElements.get(i).getText();
			// Kiem tra so voi Text can chon
			if (Actualtem.equals(ExpectedItem)) {
				ListAllElements.get(i).click();
				break;
												}
										}
																					}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

